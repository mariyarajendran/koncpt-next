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
import app.technotech.koncpt.data.network.model.LiveClassesListModel;
import app.technotech.koncpt.databinding.FragmentCompletedZoomBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.zoomadapter.CompletedZoomAdapter;
import app.technotech.koncpt.ui.viewmodels.OnLiveViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import app.technotech.koncpt.utils.TextUtil;
import es.dmoral.toasty.Toasty;

public class CompletedZoomFragment extends Fragment {
    private String mSubjectId = "";
    private FragmentCompletedZoomBinding binding;
    private OnLiveViewModel model;
    private AlertDialog progressDialog;
    private AppSharedPreference sharedPreference;
    private GeneralUtils generalUtils;
    private boolean isLoaded = false, isVisibleToUser;
    private CompletedZoomAdapter completedZoomAdapter;

    public static CompletedZoomFragment getInstance(String subjectId) {
        CompletedZoomFragment fragment = new CompletedZoomFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_zoom, container, false);
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
        initUI();
        if (isVisibleToUser && (!isLoaded)) {
            onApiCallList();
            isLoaded = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && isAdded()) {
            onApiCallList();
            isLoaded = true;
        }
    }

    private void initUI() {
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        sharedPreference = new AppSharedPreference(getActivity());
        setHasOptionsMenu(true);
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }

    private void onApiCallList() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.LiveClassList.getValue());
        params.put("subject_id", TextUtil.cutNull(mSubjectId));
        params.put("user_id", sharedPreference.getUserResponse().getId() + "");
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("type", "1");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getOnLiveData(params).observe(getActivity(), liveClassesListModel -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (liveClassesListModel != null) {
                    if (liveClassesListModel.getStatus() == 1) {
                        if (liveClassesListModel.getLiveClassData() != null && liveClassesListModel.getLiveClassData().size() > 0) {
                            loadData(liveClassesListModel);
                        }
                    } else {
                        Toasty.error(getActivity(), liveClassesListModel.getMessage()).show();
                    }
                }
            }
        }, 800));
    }

    private void loadData(LiveClassesListModel liveClassesListModel) {
        binding.rvCompletedZoom.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvCompletedZoom.setItemAnimator(new DefaultItemAnimator());
        completedZoomAdapter = new CompletedZoomAdapter(getActivity(), liveClassesListModel.getLiveClassData(), (int position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("scheduled_id", liveClassesListModel.getLiveClassData().get(position).getId());
            bundle.putString("subject_id", mSubjectId);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.recordedZoomListFragment, bundle);
        });
        binding.rvCompletedZoom.setAdapter(completedZoomAdapter);
    }
}
