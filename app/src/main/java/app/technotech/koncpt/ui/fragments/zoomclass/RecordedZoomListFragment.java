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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.RecordedDataModel;
import app.technotech.koncpt.databinding.FragmentRecordedZoomListBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.zoomadapter.RecordedZoomAdapter;
import app.technotech.koncpt.ui.viewmodels.OnLiveViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class RecordedZoomListFragment extends Fragment {
    private FragmentRecordedZoomListBinding binding;
    private OnLiveViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private RecordedZoomAdapter recordedZoomAdapter;
    private String mSubjectId = "";
    private String mScheduledId = "";

    public static RecordedZoomListFragment getInstance(String subjectId) {
        RecordedZoomListFragment fragment = new RecordedZoomListFragment();
        Bundle args = new Bundle();
        args.putString("subject_id", subjectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (getArguments() != null) {
            mSubjectId = bundle.getString("subject_id");
            mScheduledId = bundle.getString("scheduled_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recorded_zoom_list, container, false);
        model = new ViewModelProvider(this).get(OnLiveViewModel.class);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
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

    private void init() {
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        setHasOptionsMenu(true);
        onCompletedZoomListApi();
    }

    private void onCompletedZoomListApi() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.RecordedVideo.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("scheduled_id", mScheduledId);
        params.put("subject_id", mSubjectId);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getRecordedVideo(params).observe(getActivity(), recordedDataModel -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (recordedDataModel != null) {
                    if (recordedDataModel.getStatus() == 1) {
                        if (recordedDataModel.getData() != null && recordedDataModel.getData().size() > 0) {
                            loadData(recordedDataModel);
                        }
                    } else {
                        Toasty.error(getActivity(), recordedDataModel.getMessage()).show();
                    }
                }
            }
        }, 500));
    }

    private void loadData(RecordedDataModel recordedDataModel) {
        binding.rvRecordedZoom.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvRecordedZoom.setItemAnimator(new DefaultItemAnimator());
        recordedZoomAdapter = new RecordedZoomAdapter(getActivity(), recordedDataModel.getData(), (int position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("video_url", recordedDataModel.getData().get(position).getVideoUrl());
            Navigation.findNavController(binding.getRoot()).navigate(R.id.recordedVideoPlayerFragment, bundle);

        });
        binding.rvRecordedZoom.setAdapter(recordedZoomAdapter);
    }
}
