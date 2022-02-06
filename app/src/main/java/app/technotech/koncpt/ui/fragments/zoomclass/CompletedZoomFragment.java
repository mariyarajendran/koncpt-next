package app.technotech.koncpt.ui.fragments.zoomclass;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.databinding.FragmentCompleteClassesBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.viewmodels.OnLiveViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;

public class CompletedZoomFragment extends Fragment {
    private String mSubjectId = "";
    private FragmentCompleteClassesBinding binding;
    private OnLiveViewModel model;
    private AlertDialog progressDialog;
    private AppSharedPreference sharedPreference;
    private GeneralUtils generalUtils;
    private boolean isLoaded = false, isVisibleToUser;

    public static OnLiveClassFragment getInstance(String subjectId) {
        OnLiveClassFragment fragment = new OnLiveClassFragment();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        if (isVisibleToUser && (!isLoaded)) {
            isLoaded = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && isAdded()) {
            isLoaded = true;
        }
    }

    private void initUI() {
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        sharedPreference = new AppSharedPreference(getActivity());
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }
    }
}
