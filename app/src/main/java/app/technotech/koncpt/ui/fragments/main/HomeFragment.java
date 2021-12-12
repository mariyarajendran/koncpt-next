package app.technotech.koncpt.ui.fragments.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.BuildConfig;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.AnswerMcqOfTheDayModel;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.databinding.FragmentHomeBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.SuggestedCourseAdapter;
import app.technotech.koncpt.ui.adapter.SuggestedMaterClassAdapter;
import app.technotech.koncpt.ui.adapter.SuggestedQuestionBankAdapter;
import app.technotech.koncpt.ui.dialogs.SendOTPDialogFragment;
import app.technotech.koncpt.ui.viewmodels.HomeScreenViewModel;
import app.technotech.koncpt.ui.viewmodels.HomeViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment implements SuggestedCourseAdapter.OnTestItemClicked, SuggestedQuestionBankAdapter.OnMcqItemSelected, SuggestedMaterClassAdapter.OnMasterSelect, View.OnTouchListener {
    private FragmentHomeBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private HomeScreenViewModel model;
    private HomeScreenModel homeScreenData;
    private int mcqAnswer = -1;
    private Context mContext;
    private BottomNavigationView bottomNavigationView;
    private CircularImageView headerImageView;
    private AppSharedPreference sharedPreference;
    private NavController navController;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(HomeScreenViewModel.class);
        binding.setHomeViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        init();
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        String hash = GeneralUtils.getMd5Hash(BuildConfig.APPLICATION_ID);
        DebugLog.e("Key => " + hash);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        initAPiCall();
    }

    private void init() {
        utils = new GeneralUtils((MainActivity) requireActivity());
        progressDialog = utils.showProgressDialog();
        navController = ((MainActivity) requireActivity()).getmNavController();
        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        headerImageView = ((MainActivity) requireActivity()).getCircularImage();
        sharedPreference = utils.getAppSharedPreference();
        if (bottomNavigationView.getVisibility() == View.GONE) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

    private void initAPiCall() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.HomeScreen.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        model.getHoeScreenData(params).observe(getActivity(), new Observer<HomeScreenModel>() {
            @Override
            public void onChanged(HomeScreenModel homeScreenModel) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (homeScreenModel != null) {
                                if (homeScreenModel.getStatus() == 1) {
                                    homeScreenData = homeScreenModel;
                                    sharedPreference.saveHomeScreenData(new Gson().toJson(homeScreenModel));
                                } else {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                }
                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onTestClick(int position, HomeScreenModel.TestSeries data) {

    }

    @Override
    public void onMcqItemClick(int position, HomeScreenModel.PopularMcq data) {

    }

    @Override
    public void onMasterItemClick(int position, HomeScreenModel.SuggestedVideo data) {
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}