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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.LiveClassesListModel;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.databinding.FragmentOnLiveClassBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.EnrollClassAdapter;
import app.technotech.koncpt.ui.adapter.OnLiveClassAdapter;
import app.technotech.koncpt.ui.dialogs.CustomBuyNowDialogFragment;
import app.technotech.koncpt.ui.viewmodels.OnLiveViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import app.technotech.koncpt.utils.TextUtil;
import es.dmoral.toasty.Toasty;
import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.ZoomSDK;

public class OnLiveClassFragment extends Fragment implements OnLiveClassAdapter.OnLiveClassItemClick, EnrollClassAdapter.OnEnrollItemListener, CustomBuyNowDialogFragment.OnNavigateToScreen {
    private FragmentOnLiveClassBinding binding;
    private OnLiveViewModel model;
    private AlertDialog progressDialog;
    private GeneralUtils generalUtils;
    private AppSharedPreference sharedPreference;
    private OnLiveClassAdapter adapter;
    private EnrollClassAdapter enrollClassAdapter;
    private List<LiveClassesListModel.LiveClassDatum> liveClassData = new ArrayList<>();
    private List<LiveClassesListModel.EnrollDatum> enrollData = new ArrayList<>();
    private String mSubjectId = "";
    private boolean isLoaded = false, isVisibleToUser;

    public static OnLiveClassFragment getInstance(String param) {
        OnLiveClassFragment fragment = new OnLiveClassFragment();
        Bundle args = new Bundle();
        args.putString("subject_id", param);
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_live_class, container, false);
        model = new ViewModelProvider(this).get(OnLiveViewModel.class);
        binding.setLiveViewModel(model);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
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

    private void onApiCallList() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.LiveClassList.getValue());
        params.put("subject_id", TextUtil.cutNull(mSubjectId));
        params.put("user_id", sharedPreference.getUserResponse().getId() + "");
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("type", "0");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getOnLiveData(params).observe(getActivity(), liveClassesListModel -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (liveClassesListModel != null) {
                        if (liveClassesListModel.getStatus() == 1) {
                            liveClassData.clear();
                            liveClassData.addAll(liveClassesListModel.getLiveClassData());
                            enrollData.clear();
                            enrollData.addAll(liveClassesListModel.getEnrollData());
                            if (liveClassData.size() > 0) {
                                loadHorizontal();
                            }
                            if (enrollData.size() > 0) {
                                loadEnroll();
                            }
                            if (adapter != null && liveClassData.size() > 0) {
                                adapter.notifyItemRangeChanged(0, liveClassData.size());
                            }
                            if (adapter != null && enrollData.size() > 0) {
                                enrollClassAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toasty.error(getActivity(), liveClassesListModel.getMessage()).show();
                        }
                    } else {
                        Toasty.error(getActivity(), "Oops ! Something Went Wrong").show();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 800));
    }

    private void loadEnroll() {
        binding.textViewUpComing.setVisibility(View.VISIBLE);
    }

    private void loadHorizontal() {
        binding.textViewEnroll.setVisibility(View.VISIBLE);
    }

    private void initUI() {
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        sharedPreference = new AppSharedPreference(getActivity());
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }
        final LinearLayoutManager layoutManagerHorionatl = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.rvHorizontalLive.setLayoutManager(layoutManagerHorionatl);
        binding.rvHorizontalLive.setItemAnimator(new DefaultItemAnimator());
        adapter = new OnLiveClassAdapter(getActivity(), liveClassData, this);
        binding.rvHorizontalLive.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvHorizontalLive);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.rvEnrollUser.setLayoutManager(layoutManager);
        binding.rvEnrollUser.setItemAnimator(new DefaultItemAnimator());
        binding.rvEnrollUser.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        enrollClassAdapter = new EnrollClassAdapter(getActivity(), enrollData, this);
        binding.rvEnrollUser.setAdapter(enrollClassAdapter);
    }

    @Override
    public void onViewDetailItemClick(LiveClassesListModel.LiveClassDatum data, int position) {
    }

    @Override
    public void onEnrollItemClick(LiveClassesListModel.LiveClassDatum data, int position) {
        onApiEnroll("1", data.getId());
    }

    @Override
    public void onUnenroll(LiveClassesListModel.EnrollDatum data, int position) {
        onApiEnroll("0", Integer.toString(data.getId()));
    }

    @Override
    public void onViewDetail(LiveClassesListModel.EnrollDatum data, int position) {

    }

    @Override
    public void onJoinNow(LiveClassesListModel.EnrollDatum data, int position) {
        joinMeeting(data.getTitle(), data.getZoomUsername(), data.getZoomPassword());
    }

    private void onApiEnroll(String type, String id) {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.EnrollementForLiveClass.getValue());
        params.put("user_id", sharedPreference.getUserResponse().getId() + "");
        params.put("live_schedule_id", id);
        params.put("flag", type);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getEnrollClass(params).observe(getActivity(), new Observer<MessageModel>() {
            @Override
            public void onChanged(MessageModel messageModel) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (messageModel != null) {
                                if (messageModel.getStatus() == 1) {
                                    Toasty.success(getActivity(), messageModel.getMessage()).show();
                                    onApiCallList();
                                } else {
                                    Toasty.info(getActivity(), messageModel.getMessage()).show();
                                }
                            } else {
                                Toasty.error(getActivity(), "Oops! something went wrong").show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 500);
            }
        });
    }

    public int joinMeeting(String title, String meetingId, String passcode) {
        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
        JoinMeetingOptions meetingOptions = new JoinMeetingOptions();
        JoinMeetingParams meetingParams = new JoinMeetingParams();
        meetingParams.displayName = title;
        meetingParams.meetingNo = meetingId;
        meetingParams.password = passcode;
        return meetingService.joinMeetingWithParams(((MainActivity) requireActivity()).getApplicationContext(), meetingParams, meetingOptions);
    }

    @Override
    public void onItemNavigation() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.buyNowFragment);
    }
}