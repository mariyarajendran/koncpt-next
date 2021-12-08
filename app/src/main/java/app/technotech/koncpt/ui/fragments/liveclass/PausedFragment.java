package app.technotech.koncpt.ui.fragments.liveclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.databinding.FragmentAllBinding;
import app.technotech.koncpt.ui.adapter.PausedAdapter;
import app.technotech.koncpt.ui.viewmodels.LiveVideoViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;

public class PausedFragment extends Fragment implements PausedAdapter.OnVideoItemSelectedListener {
    private String subject_id;
    PausedAdapter pausedAdapter;
    private FragmentAllBinding binding;
    private LiveVideoViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    String plan;

    public static PausedFragment getInstance(String params) {
        Bundle bundle = new Bundle();
        PausedFragment allFragment = new PausedFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(LiveVideoViewModel.class);
        binding.setVideoViewModel(model);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        SharedPreferences prfs = getActivity().getSharedPreferences("plan", Context.MODE_PRIVATE);
        plan = prfs.getString("plan", "");
        sendPost();
    }

    private void sendPost() {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("subject_id", subject_id);
        params.put("type", "2");
        params.put(EnumApiAction.action.getValue(), EnumApiAction.VideoTopicList.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getVideoData(params).observe(getActivity(), new Observer<VideoModel>() {
            @Override
            public void onChanged(VideoModel videoModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (videoModel != null) {
                            if (videoModel.getStatus() == 1) {
                                binding.rltNoVideo.setVisibility(View.GONE);
                                binding.recyclerViewTopic.setVisibility(View.VISIBLE);
                                loadData(videoModel);
                            } else {
                                binding.rltNoVideo.setVisibility(View.VISIBLE);
                                binding.recyclerViewTopic.setVisibility(View.GONE);
                                DebugLog.e("Mo Data found");
                            }
                        }

                    }
                }, 500);
            }
        });
    }

    private void loadData(VideoModel videoModel) {
        binding.recyclerViewTopic.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewTopic.setItemAnimator(new DefaultItemAnimator());
        pausedAdapter = new PausedAdapter(getActivity(), videoModel.getData().getModuleData(), this);
        binding.recyclerViewTopic.setAdapter(pausedAdapter);
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    public void onVideoItemClick(VideoModel.ModuleDatum data, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("video_id", Integer.toString(data.getId()));
        bundle.putString("subject_id", subject_id);
        bundle.putString("topic_name", data.getClassTitle());
        bundle.putString("pause_time", data.getPaushedTime());
        Navigation.findNavController(binding.getRoot()).navigate(R.id.mainTabFragment, bundle);
    }
}
