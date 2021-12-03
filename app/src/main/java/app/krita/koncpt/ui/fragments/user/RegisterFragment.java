package app.technotech.koncpt.ui.fragments.user;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.krita.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.application.BaseApp;
import app.technotech.koncpt.data.network.model.CheckEmailModel;
import app.technotech.koncpt.data.network.model.CollegeListModel;
import app.technotech.koncpt.data.network.model.StateListModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.databinding.FragmentRegisterBinding;
import app.technotech.koncpt.ui.callbacks.RegisterCallback;
import app.technotech.koncpt.ui.viewmodels.RegisterViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import app.technotech.koncpt.utils.PasswordValidator;
import es.dmoral.toasty.Toasty;

public class RegisterFragment extends Fragment {


    private FragmentRegisterBinding binding;
    private RegisterViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    private List<StateListModel.Data> list = new ArrayList<>();
    private List<CollegeListModel.Data> collegeListModels = new ArrayList<>();

    private List<String> stateList;
    private List<String> collegeList;


    private String yearId;

    private String stateId;
    private String stateName;

    private String collegeId;
    private String collegeName;

    private String phone_number = "";
    private String password;
    private String name;
    private String email;
    private String course;

    private PasswordValidator passwordValidator;

    String[] degreeYear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (getArguments() != null) {


            if (getArguments().getInt("status") == 0) {
                phone_number = bundle.getString("mobile_no");
            } else {
                // do Nothing
            }
        }

//        if (bundle.getString("mobile_no") != null) {
//
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(RegisterViewModel.class);
        binding.setRegisterViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        bindYearsSpinner();
        stateListApi();

        buttonClickListenerBind();


        if (!TextUtils.isEmpty(phone_number)) {
            binding.edtNumber.setText(phone_number);
        }
    }

    private void collegeListInit() {

    }

    private void buttonClickListenerBind() {

        binding.setRegisterCallback(new RegisterCallback() {
            @Override
            public void onRegisterUser() {

                if (((BaseApp) requireActivity().getApplication()).isInternetAvailable()) {

                    if (validation()) {
                        if (((BaseApp) requireActivity().getApplication()).isInternetAvailable()) {

                            CheckEmailExistApi();

                        }
                    }

                }

            }
        });
    }

    private void initUI() {

        utils = new GeneralUtils(getContext());
        passwordValidator = new PasswordValidator();
        progressDialog = utils.showProgressDialog();

    }

    private void bindYearsSpinner() {

        degreeYear = getActivity().getResources().getStringArray(R.array.degree);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, degreeYear);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spCurrentYear.setAdapter(arrayAdapter);
        binding.spCurrentYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void stateListApi() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getStateList().observe(getActivity(), new Observer<StateListModel>() {
            @Override
            public void onChanged(StateListModel stateListModel) {


                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (stateListModel != null) {
                            if (stateListModel.getStatus() == 1) {

                                list = new ArrayList<>();
                                list = stateListModel.getData();

                                stateList = new ArrayList<>();
                                stateList.add("Please Select State");
                                for (int i = 0; i < list.size(); i++) {
                                    stateList.add(list.get(i).getName());
                                }
                            }
                            loadStateData();


                        }

                    }
                }, 500);

            }
        });


    }

    private void loadStateData() {

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, stateList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        binding.spState.setAdapter(stateAdapter);
        binding.spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    ((TextView) view).setTextColor(Color.BLACK);
                    stateId = list.get(i - 1).getId();
                    stateName = list.get(i - 1).getName();

//                    if (((BaseApp) requireActivity().getApplication()).isInternetAvailable()) {
//                        collegeListApi();
//                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void collegeListApi() {


        Map<String, String> map = new HashMap<>();
        map.put("state_id", stateId);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCollegeList(map).observe(getActivity(), new Observer<CollegeListModel>() {
            @Override
            public void onChanged(CollegeListModel collegeListModel) {


                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        String jsonData = new Gson().toJson(collegeListModel);
                        DebugLog.e("Response  => " + jsonData);

                        if (collegeListModel != null) {
                            if (collegeListModel.getStatus() == 1) {

                                collegeListModels = new ArrayList<>();
                                collegeList = new ArrayList<>();

                                collegeListModels = collegeListModel.getData();
                                for (int i = 0; i < collegeListModels.size(); i++) {
                                    collegeList.add(collegeListModels.get(i).getClgname());
                                }
                                collegeList.add(0, "Please select your college");
                                loadCollegeData();
                            } else {
                                Toasty.warning(getActivity(), collegeListModel.getMessage()).show();
                                collegeList = new ArrayList<>();
                                collegeListModels = new ArrayList<>();
                                loadCollegeData();
                            }
                        }


                    }
                }, 500);

            }
        });

    }

    private void loadCollegeData() {

    }


    private boolean validation() {

        if (TextUtils.isEmpty(binding.edtFullName.getText().toString())) {
            Toasty.warning(getActivity(), "Full name field shouldn't be empty").show();
            return false;
        }


        if (TextUtils.isEmpty(binding.edtEmailId.getText().toString())) {
            Toasty.warning(getActivity(), "Email id field shouldn't be empty").show();
            return false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmailId.getText().toString()).matches()) {
            Toasty.warning(getActivity(), "please enter valid email address").show();
            return false;
        }

        if (!isValidPhoneNumber(binding.edtNumber.getText().toString())) {
            Toasty.warning(getActivity(), "please enter valid mobile number").show();
            return false;
        }


        if (TextUtils.isEmpty(binding.edtPhoneNumber.getText().toString())) {
            Toasty.warning(getActivity(), "password field shouldn't be empty").show();
            return false;
        }


        if (!passwordValidator.validate(binding.edtPhoneNumber.getText().toString())) {
            Toasty.warning(getActivity(), "please enter valid password").show();
            return false;
        }


        if (TextUtils.isEmpty(binding.edtConfirmPassword.getText().toString())) {
            Toasty.warning(getActivity(), "Confirm password field shouldn't be empty").show();
            return false;
        }


        if (!binding.edtPhoneNumber.getText().toString().equals(binding.edtConfirmPassword.getText().toString())) {
            Toasty.warning(getActivity(), "Password and Confirm password must match").show();
            return false;
        }


        if (binding.spCurrentYear.getSelectedItemPosition() == 0) {
            Toasty.warning(getActivity(), "please select current academic year").show();
            return false;
        }

        if (binding.spState.getSelectedItemPosition() == 0) {
            Toasty.warning(getActivity(), "please select state").show();
            return false;
        }

        if (TextUtils.isEmpty(binding.spCollege.getText().toString())) {
            binding.spCollege.setError("Please enter collage name");
            binding.spCollege.requestFocus();
            return false;
        }

//        if (binding.spCollege.getSelectedItem() == null) {
//            Toasty.warning(getActivity(), "please select college").show();
//            return false;
//        }
//
//        if (binding.spCollege.getSelectedItemPosition() == 0) {
//            Toasty.warning(getActivity(), "Please select college").show();
//            return false;
//        }

        if (binding.radioCourseGroup.getCheckedRadioButtonId() == R.id.rd_btn_neet_pg) {
            course = "1";
        } else {
            course = "2";
        }


        phone_number = binding.edtNumber.getText().toString();
        name = binding.edtFullName.getText().toString();
        email = binding.edtEmailId.getText().toString();
        password = binding.edtPhoneNumber.getText().toString();
        yearId = String.valueOf(binding.spCurrentYear.getSelectedItemPosition());
        stateId = String.valueOf(list.get(binding.spState.getSelectedItemPosition() - 1).getId());
        collegeId = binding.spCollege.getText().toString();

        return true;
    }


    private void registerCallApi() {
        String deviceId = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.Registration.getValue());
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        params.put("password_confirmation", password);
        params.put("fullname", name);
        params.put("course", course);
        params.put("curr_acad_year", yearId);
        params.put("state_id", stateId);
        params.put("college_id", collegeId);
        params.put("phone_no", phone_number);
        params.put("device_name", "android");
        params.put("device_id", deviceId);
        DebugLog.e("Register Data => " + params.toString());
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getUserRegister(params).observe(getActivity(), new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin userModelLogin) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (userModelLogin != null) {

                            try {


                                if (userModelLogin.getStatus() == 1) {


                                    String json = new Gson().toJson(userModelLogin);


                                    String otp = Integer.toString(userModelLogin.getOtp());
                                    String phone_no = userModelLogin.getData().getPhone();

                                    Bundle args = new Bundle();
                                    args.putString("mobile_no", phone_no);
                                    args.putString("otp", otp);


                                    Fragment fragment = new OtpVerificationFragment();
                                    fragment.setArguments(args);
                                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.container, fragment);
                                    fragmentTransaction.addToBackStack(null);
                                    fragmentTransaction.commit();
//
//                                Intent mIntent = new Intent(getActivity(), MainActivity.class);
//                                startActivity(mIntent);
//                                requireActivity().finish();

                                } else {

                                    Toasty.error(getActivity(), userModelLogin.getMessage()).show();

//
                                }


                            } catch (Exception ex) {

                            }


                        } else {
                            Toasty.error(getActivity(), "Oops Something went wrong").show();
                        }

                    }
                }, 500);

            }
        });

    }


    private void CheckEmailExistApi() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.CheckEmail.getValue());
        params.put("email", binding.edtEmailId.getText().toString());
        params.put("phone", binding.edtNumber.getText().toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getMailCheck(params).observe(getActivity(), new Observer<CheckEmailModel>() {
            @Override
            public void onChanged(CheckEmailModel checkEmailModel) {


                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (checkEmailModel != null) {

                            if (checkEmailModel.getStatus() == 1) {

                                registerCallApi();

                            } else if (checkEmailModel.getStatus() == 0) {


                                Toasty.info(getActivity(), checkEmailModel.getMessage()).show();

                            }
                        }

                    }
                }, 500);
            }
        });

    }


    private boolean isValidPhoneNumber(String phone) {

        if (!phone.trim().equals("") && phone.length() == 10) {
            return Patterns.PHONE.matcher(phone).matches();
        }

        return false;
    }

//
}


