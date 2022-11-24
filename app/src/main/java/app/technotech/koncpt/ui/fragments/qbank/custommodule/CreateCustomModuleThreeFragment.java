package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.FragmentCreateCustomModuleThreeBinding;
import app.technotech.koncpt.ui.callbacks.CustomModuleOneCallback;
import app.technotech.koncpt.ui.viewmodels.CustomModuleViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class CreateCustomModuleThreeFragment extends Fragment {


    private FragmentCreateCustomModuleThreeBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private Context mContext;
    private CustomModuleViewModel model;

    private String jsonData;
    private String parameter_1;
    private String parameter_2;
    private String parameter_3;
    private String parameter_4;
    private String parameter_5;
    private String parameter_6;
    private String parameter_7;
    private String parameter_8;
    private String mode = "";
    Bundle mBundle = new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle =getArguments();

        if (bundle != null){
            mBundle = bundle;
            parameter_1 = bundle.getString("levels", "");
            parameter_2 = bundle.getString("questions", "");
            parameter_3 = bundle.getString("questions_from", "");
            parameter_4 = bundle.getString("subject_list", "");
            parameter_5 = bundle.getString("tags", "");
            parameter_6 = bundle.getString("subject_name_list", "");
            parameter_7 = bundle.getString("subject_count", "");
            parameter_8 = bundle.getString("tags_size", "");



//

        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_custom_module_three, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(CustomModuleViewModel.class);
        binding.setCustomViewModel(model);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        buttonClickListener();



       binding.cardExamMode.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               binding.radioBtnExam.setChecked(true);
               binding.radioBtnRegular.setChecked(false);
               mode = "Exam";

           }
       });

       binding.cardRegularMode.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               binding.radioBtnExam.setChecked(false);
               binding.radioBtnRegular.setChecked(true);
               mode = "Regular";

           }
       });


       binding.radioBtnExam.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               binding.radioBtnExam.setChecked(true);
               binding.radioBtnRegular.setChecked(false);
               mode = "Exam";
           }
       });

       binding.radioBtnRegular.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               binding.radioBtnExam.setChecked(false);
               binding.radioBtnRegular.setChecked(true);
               mode = "Regular";
           }
       });

    }

    private void buttonClickListener() {


        binding.setCustomCallback(new CustomModuleOneCallback() {
            @Override
            public void onPrevious() {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }

            @Override
            public void onNext() {
//

                if (!TextUtils.isEmpty(mode)){
                    onApiCall();
                } else {
                    Toasty.info(mContext, "Please select mode").show();
                }

            }
        });

    }

    private void onApiCall() {


        Map<String, String> params = new HashMap<>();
        params.put("level[]", parameter_1);
        params.put("total_question", parameter_2);
        params.put("question_from", parameter_3);
        params.put("subjectname_from[]", parameter_4);
        params.put("tags[]", parameter_5);
        params.put("mode", mode);
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));

        if (!progressDialog.isShowing()){
            progressDialog.show();
        }


        model.getCustomModelData(params).observe(getActivity(), new Observer<CustomExamModel>() {
            @Override
            public void onChanged(CustomExamModel customExamModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                        if (customExamModel!=null){
                            if (customExamModel.getStatus() == 1){


                                try{

                                    JSONObject object = new JSONObject();
                                    object.put("tags_size", parameter_8);
                                    object.put("mode", mode);
                                    object.put("questions", parameter_2);
                                    object.put("subject_count", parameter_7);
                                    if (parameter_1.equals("easy,medium,hard")){
                                        object.put("levels", "All");
                                    } else {
                                        object.put("levels", parameter_1);
                                    }
                                    customExamModel.setExtraData(object.toString());

                                } catch ( Exception ex){
                                    ex.printStackTrace();
                                }

                                String jsonData = new Gson().toJson(customExamModel);

                                DebugLog.e("Data" + jsonData);

                                Bundle bundle = new Bundle();
                                bundle.putString("levels", parameter_1);
                                bundle.putString("questions", parameter_2);
                                bundle.putString("questions_from", parameter_3);
                                bundle.putString("subject_list", parameter_4);
                                bundle.putString("tags", parameter_5);
                                bundle.putString("subject_name_list", parameter_6);
                                bundle.putString("subject_count", parameter_7);
                                bundle.putString("tags_size", parameter_8);
                                bundle.putString("mode", mode);
                                bundle.putString("data", jsonData);
//                                bundle.putString("subject_name_list", subjectName);

                                new AppSharedPreference(getActivity()).saveCustomModule(jsonData);
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_createCustomModuleThreeFragment_to_selectModuleFragment, bundle);

                            } else {
                                Toasty.success(mContext, customExamModel.getMessage()).show();
                            }
                        }
                    }
                }, 300);

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

    private void onApiCallString() {


        Map<String, String> params = new HashMap<>();
        params.put("level[]", parameter_1);
        params.put("total_question", parameter_2);
        params.put("question_from", parameter_3);
        params.put("subjectname_from[]", parameter_4);
        params.put("tags[]", parameter_5);
        params.put("mode", mode);
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));

        if (!progressDialog.isShowing()){
            progressDialog.show();
        }


        model.getCustomModelDataString(params).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String customExamModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }

                        if (customExamModel!=null){

                            String data = customExamModel;
                            String data2 = customExamModel;

                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);
                            DebugLog.e("Response : " + customExamModel);

//                            if (customExamModel.getStatus() == 1){
//
//
//                                try{
//
//                                    JSONObject object = new JSONObject();
//                                    object.put("tags_size", parameter_8);
//                                    object.put("mode", mode);
//                                    object.put("questions", parameter_2);
//                                    object.put("subject_count", parameter_7);
//                                    if (parameter_1.equals("easy,medium,hard")){
//                                        object.put("levels", "All");
//                                    } else {
//                                        object.put("levels", parameter_1);
//                                    }
//                                    customExamModel.setExtraData(object.toString());
//
//                                } catch ( Exception ex){
//                                    ex.printStackTrace();
//                                }
//
//                                String jsonData = new Gson().toJson(customExamModel);
//
//                                DebugLog.e("Data" + jsonData);
//
//                                Bundle bundle = new Bundle();
//                                bundle.putString("levels", parameter_1);
//                                bundle.putString("questions", parameter_2);
//                                bundle.putString("questions_from", parameter_3);
//                                bundle.putString("subject_list", parameter_4);
//                                bundle.putString("tags", parameter_5);
//                                bundle.putString("subject_name_list", parameter_6);
//                                bundle.putString("subject_count", parameter_7);
//                                bundle.putString("tags_size", parameter_8);
//                                bundle.putString("mode", mode);
//                                bundle.putString("data", jsonData);
////                                bundle.putString("subject_name_list", subjectName);
//
//                                new AppSharedPreference(getActivity()).saveCustomModule(jsonData);
//                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_createCustomModuleThreeFragment_to_selectModuleFragment, bundle);
//
//                            } else {
//                                Toasty.success(mContext, customExamModel.getMessage()).show();
//                            }
                        }
                    }
                }, 300);

            }
        });



    }

}