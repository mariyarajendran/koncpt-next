package app.technotech.koncpt.ui.fragments.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.DailyHuntModel;
import app.technotech.koncpt.databinding.FragmentDailyHuntBinding;
import app.technotech.koncpt.ui.adapter.DailyHuntAdapter;
import app.technotech.koncpt.ui.viewmodels.HuntViewModel;
import app.technotech.koncpt.utils.GeneralUtils;

public class DailyHuntFragment extends Fragment {
    private FragmentDailyHuntBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private HuntViewModel model;
    private MenuItem menuItem;

    private List<DailyHuntModel.Datum> modelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_hunt, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(HuntViewModel.class);
        binding.setHundViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        callApi();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void callApi() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getHuntData().observe(getActivity(), dailyHuntModel -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (dailyHuntModel != null) {
                    if (dailyHuntModel.getStatus() == 1) {
                        modelList = dailyHuntModel.getData();
                        loadData();
                    }

                }
            }
        }, 500));
    }

    private void loadData() {
        binding.recyclerHunt.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerHunt.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerHunt.setHasFixedSize(true);
        DailyHuntAdapter adapter = new DailyHuntAdapter(getActivity(), modelList, (datum, position) -> {
//                String json = new Gson().toJson(datum);
//                Intent mIntent = new Intent(getActivity(), DailyHuntDetailView.class);
//                mIntent.putExtra("data", json);
//                startActivity(mIntent);
        });
        binding.recyclerHunt.setAdapter(adapter);
    }


}