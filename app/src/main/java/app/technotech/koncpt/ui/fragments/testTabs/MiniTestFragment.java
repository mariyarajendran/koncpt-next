package app.technotech.koncpt.ui.fragments.testTabs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.TestEntity;
import app.technotech.koncpt.data.network.model.GrandTestModelResponse;
import app.technotech.koncpt.databinding.FragmentMiniTestBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.testadapter.GrandTestRecyclerAdapter;
import app.technotech.koncpt.ui.dialogs.CustomBuyNowDialogFragment;
import app.technotech.koncpt.ui.interfaces.TestListener;
import app.technotech.koncpt.ui.viewmodels.GrandTestViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class MiniTestFragment extends Fragment implements TestListener, GrandTestRecyclerAdapter.OnItemClickListener, CustomBuyNowDialogFragment.OnNavigateToScreen {


    private Context mContext;
    private View mView;
    private FragmentMiniTestBinding binding;
    private GeneralUtils utils;
    private GrandTestViewModel model;
    private AlertDialog progressDialog;
    private List<GrandTestModelResponse.Datum> list = new ArrayList<>();
    private GrandTestModelResponse grandTestData;


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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mini_test, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(GrandTestViewModel.class);
        binding.setGrandViewModel(model);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        onApiCall();
    }

    private void init() {
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
    }

    private void onApiCall() {

        Map<String, String> params = new HashMap<>();
        params.put("type", "0");
        params.put("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getGrandTestData(params).observe(getActivity(), new Observer<GrandTestModelResponse>() {
            @Override
            public void onChanged(GrandTestModelResponse grandTestModelResponse) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                        if (grandTestModelResponse != null) {

                            if (grandTestModelResponse.getStatus() == 1) {

                                grandTestData = grandTestModelResponse;
                                list = grandTestModelResponse.getData();

                                if (list.size() > 0) {
                                    loadData();
                                } else {
                                    Toasty.error(mContext, grandTestModelResponse.getMessage()).show();
                                }


                            } else {
//                                Toasty.error(mContext, grandTestModelResponse.getMessage()).show();
                            }

                        }

                    }
                }, 500);
            }
        });


    }

    private void loadData() {

        binding.revMiniTest.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.revMiniTest.setItemAnimator(new DefaultItemAnimator());
        GrandTestRecyclerAdapter recyclerAdapter = new GrandTestRecyclerAdapter(mContext, list, this);
        binding.revMiniTest.setAdapter(recyclerAdapter);


    }

    @Override
    public void onTestSelectListener(TestEntity testEntity) {

    }


    @Override
    public void onItemClick(GrandTestModelResponse.Datum datum, int position) {

        String plantType = new AppSharedPreference(getActivity()).getUserResponse().getPlan();

        if (plantType.equals("f")) {


            if (grandTestData.getData().get(position).getIsPaid().equals("1")) {
                //CallBuyNowFragment();
            } else {

                if (grandTestData.getData().get(position).getIsGiven().equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("quiz_id", datum.getId());
                    bundle.putInt("destination", 0);
                    NavController navController = ((MainActivity) requireActivity()).getmNavController();
                    navController.navigate(R.id.action_GrandTestFragmentToResultFragment, bundle);

                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("quiz_id", datum.getId());
                    bundle.putInt("destination", 1);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_GrandTestFragmentToGrandExamTest, bundle);
                }
            }

        } else {


            if (grandTestData.getData().get(position).getIsGiven().equals("1")) {
                Bundle bundle = new Bundle();
                bundle.putString("quiz_id", datum.getId());
                bundle.putInt("destination", 0);
                NavController navController = ((MainActivity) requireActivity()).getmNavController();
                navController.navigate(R.id.action_GrandTestFragmentToResultFragment, bundle);

            } else {

                Bundle bundle = new Bundle();
                bundle.putString("quiz_id", datum.getId());
                bundle.putInt("destination", 1);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_GrandTestFragmentToGrandExamTest, bundle);
            }

        }
    }

    private void CallBuyNowFragment() {
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