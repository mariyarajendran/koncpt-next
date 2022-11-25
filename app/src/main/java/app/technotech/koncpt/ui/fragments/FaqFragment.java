package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.FaqModel;
import app.technotech.koncpt.databinding.FragmentFaqBinding;
import app.technotech.koncpt.ui.adapter.FaqAdapter;
import app.technotech.koncpt.ui.viewmodels.FaqViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class FaqFragment extends Fragment {

    FragmentFaqBinding binding;
    private FaqViewModel model;
    FaqAdapter faqAdapter;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_faq, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(FaqViewModel.class);
        binding.setAboutusViewModel(model);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        getFaqData();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    private void getFaqData() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getFaqData().observe(getActivity(), new Observer<FaqModel>() {
            @Override
            public void onChanged(FaqModel dailyHuntModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (dailyHuntModel != null) {
                                String jsonData = new Gson().toJson(dailyHuntModel);
                                DebugLog.e("ResponseFaq =>>" + jsonData);
                                faqAdapter = new FaqAdapter(getActivity(),dailyHuntModel.getFaqdata());
                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
                                binding.recyclerView.setAdapter(faqAdapter);

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });


    }
}