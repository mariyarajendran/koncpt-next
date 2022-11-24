package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.AboutusModel;
import app.technotech.koncpt.databinding.FragmentAboutUsBinding;
import app.technotech.koncpt.ui.viewmodels.AboutusViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class AboutUsFragment extends Fragment {

    FragmentAboutUsBinding binding;
    private AboutusViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(AboutusViewModel.class);
        binding.setAboutusViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        getAboutusData();
    }

    private void getAboutusData() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getAboutusData().observe(getActivity(), new Observer<AboutusModel>() {
            @Override
            public void onChanged(AboutusModel dailyHuntModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (dailyHuntModel != null) {
                                String jsonData = new Gson().toJson(dailyHuntModel);
                                binding.txtAboutUs.setText(Html.fromHtml(dailyHuntModel.getContent().get(0).getContent()));
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