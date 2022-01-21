package app.technotech.koncpt.ui.fragments.liveclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.databinding.FragmentLayoutAllBinding;
import app.technotech.koncpt.ui.adapter.AllAdapter;
import app.technotech.koncpt.ui.viewmodels.LiveAllViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class AllFragment extends Fragment implements AllAdapter.OnVideoItemSelectedListener {

    private String subject_id;
    AllAdapter completedAdapter;
    private FragmentLayoutAllBinding binding;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private LiveAllViewModel model;
    private boolean isLoaded = false, isVisibleToUser;
    String plan;

    public static AllFragment getInstance(String params) {
        Bundle bundle = new Bundle();
        AllFragment allFragment = new AllFragment();
        bundle.putString("subject_id", params);
        allFragment.setArguments(bundle);
        return allFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subject_id = getArguments().getString("subject_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout_all, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(LiveAllViewModel.class);
        binding.setAllViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        SharedPreferences prfs = getActivity().getSharedPreferences("plan", Context.MODE_PRIVATE);
        plan = new AppSharedPreference(getActivity()).getUserResponse().getPlan();
        if (isVisibleToUser && (!isLoaded)) {
            sendPost();
            isLoaded = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && isAdded()) {
            sendPost();
            isLoaded = true;
        }
    }


    private void sendPost() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.VideoTopicList.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("subject_id", subject_id);
        params.put("type", "3");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getCompleteData(params).observe(getActivity(), new Observer<VideoModel>() {
            @Override
            public void onChanged(VideoModel completeModel) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (completeModel != null) {
                            if (completeModel.getStatus() == 1) {
                                if (completeModel.getData().getModuleData().size() > 0) {
                                    String json = new Gson().toJson(completeModel);
                                    DebugLog.e("JsonAll ==> " + json);
                                    binding.rltNoVideo.setVisibility(View.GONE);
                                    binding.recyclerViewTopic.setVisibility(View.VISIBLE);
                                    loadData(completeModel);
                                } else {
                                    binding.rltNoVideo.setVisibility(View.VISIBLE);
                                    binding.recyclerViewTopic.setVisibility(View.GONE);
                                    binding.rltNoVideo.setVisibility(View.VISIBLE);
                                    binding.recyclerViewTopic.setVisibility(View.GONE);
                                }
                            } else {
                                Toasty.error(getActivity(), completeModel.getMessage()).show();

                            }
                        }

                    }
                }, 500);
            }
        });
    }

    private void loadData(VideoModel data) {
        binding.recyclerViewTopic.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewTopic.setItemAnimator(new DefaultItemAnimator());
        completedAdapter = new AllAdapter(getActivity(), data.getData().getModuleData(), this);
        binding.recyclerViewTopic.setAdapter(completedAdapter);
    }


    @Override
    public void onVideoItemClick(VideoModel.ModuleDatum data, int position, String status) {
        Bundle bundle = new Bundle();
        bundle.putString("video_id", Integer.toString(data.getId()));
        bundle.putString("subject_id", subject_id);
        bundle.putString("topic_name", data.getClassTitle());
        bundle.putString("pause_time", data.getPaushedTime());
        Navigation.findNavController(binding.getRoot()).navigate(R.id.mainTabFragment, bundle);
    }
}