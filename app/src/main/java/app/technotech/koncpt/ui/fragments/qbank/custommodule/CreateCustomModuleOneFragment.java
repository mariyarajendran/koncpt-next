package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SubjectListModel;
import app.technotech.koncpt.databinding.FragmentCreateCustomModuleOneBinding;
import app.technotech.koncpt.ui.adapter.custommoduleadapters.CustomModuleSubjectAdapter;
import app.technotech.koncpt.ui.callbacks.CustomModuleOneCallback;
import app.technotech.koncpt.ui.viewmodels.CustomModuleViewModel;
import app.technotech.koncpt.utils.GeneralUtils;

public class CreateCustomModuleOneFragment extends Fragment {


    private CustomModuleSubjectAdapter mAdapter;

    private FragmentCreateCustomModuleOneBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private CustomModuleViewModel model;
    private View mView;
    private Context mContext;

    private SubjectListModel subjectData;
    private ArrayList<String> selectedSubject = new ArrayList<>();
    private String subjectList;

    private Bundle bundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_custom_module_one, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(CustomModuleViewModel.class);
        binding.setCustomViewModel(model);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        setHasOptionsMenu(true);
        init();
        buttonClickListener();


        binding.radioSubjectGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedItem = radioGroup.getCheckedRadioButtonId();

                switch (selectedItem) {

                    case R.id.rd_btn_all:
                        selectAllItem();
                        break;

                    case R.id.rd_btn_choose_subject:
                        deSelectAllItem();
                        break;
                }


            }
        });


    }

    private void deSelectAllItem() {
        for (int i = 0; i < subjectData.getData().size(); i++) {
            subjectData.getData().get(i).setSelected(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void selectAllItem() {

        for (int i = 0; i < subjectData.getData().size(); i++) {
            subjectData.getData().get(i).setSelected(true);
        }

        mAdapter.notifyDataSetChanged();

    }

    private void buttonClickListener() {

        binding.setCustomCallback(new CustomModuleOneCallback() {
            @Override
            public void onPrevious() {
                Navigation.findNavController(mView).popBackStack();
            }

            @Override
            public void onNext() {

                selectedSubject = new ArrayList<>();


                for (int i = 0; i < subjectData.getData().size(); i++) {

                    if (subjectData.getData().get(i).isSelected()) {
                        selectedSubject.add(subjectData.getData().get(i).getId() + "");
                    }
                }

                List<String> selectedSubjectName = new ArrayList<>();

                for (int i = 0; i < subjectData.getData().size(); i++) {

                    if (subjectData.getData().get(i).isSelected()) {
                        selectedSubjectName.add(subjectData.getData().get(i).getSubjectTitle());
                    }

                }

//                if (se)

                String counts_detail;

                if (selectedSubject.size() == subjectData.getData().size()){
                    counts_detail = "All";
                } else {
                    counts_detail =  selectedSubject.size() +"";
                }


                subjectList = TextUtils.join(", ", selectedSubject);
                String subjectName = TextUtils.join(", ", selectedSubjectName);


                if (selectedSubject.size() > 0) {
                    bundle.putString("subject_list", subjectList);
                    bundle.putString("subject_name_list", subjectName);
                    bundle.putString("subject_count", counts_detail);
                    Navigation.findNavController(mView).navigate(R.id.action_createCustomModuleOneFragment_to_createCustomModuleTwoFragment, bundle);
                }
            }
        });

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    private void init() {

        utils = new GeneralUtils(mContext);
        progressDialog = utils.showProgressDialog();
        onCallApi();
    }

    private void onCallApi() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getSubjectList().observe(getActivity(), new Observer<SubjectListModel>() {
            @Override
            public void onChanged(SubjectListModel subjectListModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        try {

                            if (subjectListModel != null) {

                                if (subjectListModel.getStatus() == 1) {
                                    subjectData = subjectListModel;
                                    loadData();
                                }

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

        mAdapter = new CustomModuleSubjectAdapter(mContext, (ArrayList) subjectData.getData());
        binding.revSubjectList.setLayoutManager(new LinearLayoutManager(mContext));
        binding.revSubjectList.setAdapter(mAdapter);
        binding.rdBtnAll.setChecked(true);

    }


}