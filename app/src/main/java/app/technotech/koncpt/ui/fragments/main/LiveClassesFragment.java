package app.technotech.koncpt.ui.fragments.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.ProfileModel;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.databinding.FragmentLiveClassesBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.LiveClassRecyclerAdapter;
import app.technotech.koncpt.ui.viewmodels.LiveClassSubjectModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class LiveClassesFragment extends Fragment implements LiveClassRecyclerAdapter.OnItemClickListener {
    private FragmentLiveClassesBinding binding;
    private LiveClassSubjectModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private MenuItem menuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live_classes, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(LiveClassSubjectModel.class);
        binding.setSubjectViewMode(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.GONE) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
        onApiCall();
        getProfileData();
    }

    private void getProfileData() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.ProfileDetail.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getProfileData(params).observe(getActivity(), new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel completeModel) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (completeModel != null) {
                            if (completeModel.getStatus() == 1) {
                                String json = new Gson().toJson(completeModel);
                                DebugLog.e("JsonUserProfile ==> " + json);
                                String plan = completeModel.getData().get(0).getPlan();
                                SharedPreferences preferences = getContext().getSharedPreferences("plan", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("plan", plan);
                                editor.apply();
                                // Toasty.success(getActivity(), completeModel.getMessage()).show();
                            } else {
                                Toasty.error(getActivity(), completeModel.getMessage()).show();

                            }
                        }
                    }
                }, 500);
            }
        });
    }

    private void onApiCall() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.VideoSubject.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getSubjectData(params).observe(getActivity(), new Observer<SubjectListDataModel>() {
            @Override
            public void onChanged(SubjectListDataModel subjectListDataModel) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    if (subjectListDataModel != null) {
                        if (subjectListDataModel.getStatus() == 1) {
                            loadData(subjectListDataModel);
                        } else {
                            Toasty.error(getActivity(), subjectListDataModel.getMessage()).show();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void loadData(SubjectListDataModel subjectListDataModel) {
        binding.rvLiveSubject.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        LiveClassRecyclerAdapter recyclerAdapter = new LiveClassRecyclerAdapter(getActivity(), subjectListDataModel.getData(), this);
        binding.rvLiveSubject.setAdapter(recyclerAdapter);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(true);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        menuItem = menu.findItem(R.id.action_search);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.searchHomeFragment);
                return false;
            }
        });
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void OnItemClick(int position, SubjectListDataModel.Datum data) {
        Bundle bundle = new Bundle();
        bundle.putString("subject_id", Integer.toString(data.getId()));
        bundle.putString("subject_name", data.getSubjectTitle());
        Navigation.findNavController(binding.getRoot()).navigate(R.id.subjectAllFragment, bundle);
    }
}