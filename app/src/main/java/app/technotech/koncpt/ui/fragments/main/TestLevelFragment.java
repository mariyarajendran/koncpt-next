package app.technotech.koncpt.ui.fragments.main;

import android.app.AlertDialog;
import android.content.Context;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.databinding.FragmentTestLevelBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.QBankLevelAdapter;
import app.technotech.koncpt.ui.dialogs.CustomBuyNowDialogFragment;
import app.technotech.koncpt.ui.viewmodels.BuyDetailsViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import app.technotech.koncpt.utils.TextUtil;

public class TestLevelFragment extends Fragment implements CustomBuyNowDialogFragment.OnNavigateToScreen {
    private Context mContext;
    private FragmentTestLevelBinding binding;
    private BuyDetailsViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private BottomNavigationView navigationView;
    private QBankLevelAdapter qBankLevelAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_level, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(BuyDetailsViewModel.class);
        binding.setLevelViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setButtonClickListner();
        setHasOptionsMenu(true);
        navigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (navigationView.getVisibility() == View.GONE) {
            navigationView.setVisibility(View.VISIBLE);
        }
    }

    private void setButtonClickListner() {

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
        onPlanWiseApiCall();
    }

    private void onPlanWiseApiCall() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.PlanWiseLevel.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("plan_id", "1");
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getBuyDetailsData(params).observe(getActivity(), notesModel -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
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

    private void loadData(BuyDetailsModel notesModel) {
        binding.revQBankLevel.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.revQBankLevel.setItemAnimator(new DefaultItemAnimator());
        qBankLevelAdapter = new QBankLevelAdapter(getActivity(), notesModel.getData(), (int position) -> {
            if (notesModel.getData().get(position).getLevel_active() == 1)
                navigateToQBankFragment(TextUtil.cutNull(notesModel.getData().get(position).getLevel_id()));
            else
                callBuyNowFragment();
        });
        binding.revQBankLevel.setAdapter(qBankLevelAdapter);
    }

    private void navigateToQBankFragment(String levelId) {
        Bundle bundle = new Bundle();
        bundle.putString("level_id", levelId);
        new AppSharedPreference(getActivity()).setLevelId(levelId);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.testBankFragment, bundle);
    }

    private void callBuyNowFragment() {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = requireActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new CustomBuyNowDialogFragment(this);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "dialog");
    }


    @Override
    public void onItemNavigation() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.buyNowFragment);
    }
}