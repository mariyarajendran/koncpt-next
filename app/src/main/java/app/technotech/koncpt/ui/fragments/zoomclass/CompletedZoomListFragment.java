package app.technotech.koncpt.ui.fragments.zoomclass;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.data.network.model.RecordedDataModel;
import app.technotech.koncpt.databinding.FragmentCompletedZoomListBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.zoomadapter.CompletedZoomAdapter;
import app.technotech.koncpt.ui.viewmodels.OnLiveViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;

public class CompletedZoomListFragment extends Fragment {
    private FragmentCompletedZoomListBinding binding;
    private OnLiveViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private CompletedZoomAdapter completedZoomAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_zoom_list, container, false);
        model = new ViewModelProvider(this).get(OnLiveViewModel.class);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void init() {
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        onCompletedZoomListApi();
    }

    private void onCompletedZoomListApi() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.RecordedVideo.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("scheduled_id", "40");
        params.put("subject_id", "20");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getRecordedVideo(params).observe(getActivity(), notesModel -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (notesModel != null) {
                    if (notesModel.getData() != null && notesModel.getData().size() > 0) {
                        loadData(notesModel);
                    }
                }
            }
        }, 500));
    }

    private void loadData(RecordedDataModel recordedDataModel) {
        binding.rvCompletedZoom.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvCompletedZoom.setItemAnimator(new DefaultItemAnimator());
        completedZoomAdapter = new CompletedZoomAdapter(getActivity(), recordedDataModel.getData(), (int position) -> {
//            if (notesModel.getData().get(position).getLevel_active() == 1)
//                navigateToQBankFragment(TextUtil.cutNull(notesModel.getData().get(position).getLevel_id()));
//            else
//                callBuyNowFragment();
        });
        binding.rvCompletedZoom.setAdapter(completedZoomAdapter);
    }
}
