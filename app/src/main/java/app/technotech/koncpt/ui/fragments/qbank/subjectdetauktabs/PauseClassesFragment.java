package app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.data.network.model.SubjectQuestionBankGroup;
import app.technotech.koncpt.databinding.FragmentPauseClassesBinding;
import app.technotech.koncpt.ui.adapter.testadapter.AllTestRecyclerAdapter;
import app.technotech.koncpt.ui.dialogs.CustomBuyNowDialogFragment;
import app.technotech.koncpt.ui.viewmodels.SubjectViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.GeneralUtils;
import app.technotech.koncpt.utils.TextUtil;
import es.dmoral.toasty.Toasty;

public class PauseClassesFragment extends Fragment implements AllTestRecyclerAdapter.OnItemClickListener, CustomBuyNowDialogFragment.OnNavigateToScreen {

    private Context mContext;
//    private View mView;


    /****/

    private String subject_id;
    private String subject_name;
    private String levelId;
    private GeneralUtils utils;
    private SubjectViewModel model;
    private AlertDialog progressDialog;
    private AllTestRecyclerAdapter mAdapter;
    List<SubjectQuestionBankGroup> questionBankGroups = new ArrayList<>();
    private int destination = 0;
    private FragmentPauseClassesBinding binding;
    private boolean isLoaded = false, isVisibleToUser;

    public static PauseClassesFragment getInstance(String params1, String params2, String params3) {

        PauseClassesFragment fragment = new PauseClassesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("subject_id", params1);
        bundle.putString("subject_name", params2);
        bundle.putString("level_id", params3);
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            subject_id = bundle.getString("subject_id");
            subject_name = bundle.getString("subject_name");
            levelId = bundle.getString("level_id");
            destination = bundle.getInt("destination", 0);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pause_classes, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(SubjectViewModel.class);
        binding.setSubjectViewModel(model);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        if (isVisibleToUser && (!isLoaded)) {
            callApi();
            isLoaded = true;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && isAdded()) {
            callApi();
            isLoaded = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {


        }

    }

    private void init() {

        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();

    }


    private void callApi() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.Topics.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("subject_id", TextUtil.cutNull(subject_id));
        params.put("type", "0");
        params.put("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getSubjectModel(params).observe(getActivity(), new Observer<SubjectModel>() {
            @Override
            public void onChanged(SubjectModel subjectModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        try {

                            if (subjectModel != null) {
                                if (subjectModel.getStatus() == 1) {

                                    questionBankGroups = new ArrayList<>();
                                    for (int i = 0; i < subjectModel.getData().getModuleName().size(); i++) {
                                        String moduleName = subjectModel.getData().getModuleName().get(i).getModuleName();
                                        List<SubjectModel.ModuleDatum> datumList = new ArrayList<>();
                                        for (int j = 0; j < subjectModel.getData().getModuleData().size(); j++) {
                                            if (subjectModel.getData().getModuleData().get(j).getModuleId().equals(subjectModel.getData().getModuleName().get(i).getId())) {
                                                datumList.add(subjectModel.getData().getModuleData().get(j));
                                            }
                                        }

                                        questionBankGroups.add(new SubjectQuestionBankGroup(moduleName, datumList));
                                        loadData();
                                    }
                                } else {


                                    //Toasty.info(getActivity(), subjectModel.getMessage()).show();

                                }

                            } else {
                                //Toasty.error(getActivity(), "Something Went Wrong ").show();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });

    }

    private void loadData() {

        RecyclerView.ItemAnimator animator = binding.revSubjectChapter.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        AppCompatActivity activity = (AppCompatActivity) getContext();

        mAdapter = new AllTestRecyclerAdapter(activity, questionBankGroups, this);
        binding.revSubjectChapter.setLayoutManager(new LinearLayoutManager(mContext));
        binding.revSubjectChapter.setAdapter(mAdapter);
        mAdapter.expandAllGroups();
    }


    @Override
    public void onItemClick(SubjectModel.ModuleDatum data, int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);
        bundle.putString("topic_id", data.getId());
        bundle.putInt("destination", destination);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.mcqsFragment, bundle);
    }

    @Override
    public void onItemNavigation() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.buyNowFragment);
    }

}