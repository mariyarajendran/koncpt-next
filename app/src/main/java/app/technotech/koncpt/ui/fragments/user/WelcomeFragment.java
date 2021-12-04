package app.technotech.koncpt.ui.fragments.user;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.application.BaseApp;
import app.technotech.koncpt.data.network.model.FacebookResponseModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.WelcomeModel;
import app.technotech.koncpt.databinding.FragmentWelcomeBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.WelcomeCallback;
import app.technotech.koncpt.ui.fragments.TermAndConditionFragment;
import app.technotech.koncpt.ui.viewmodels.WelcomeViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private WelcomeViewModel viewModel;

    private String phoneNumber;
    private String otp;

    private static final String EMAIL = "email";
    private CallbackManager callbackManager;

    private String name;
    private String email;
    private String ppic;
    private String imagefb;
    private String login_type;
    private String regId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFCMToken();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(getActivity()).get(WelcomeViewModel.class);
        binding.setWelcomeViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        onClickListenerBinding();
        if (new AppSharedPreference(getActivity()).isLogin()) {
            Intent mIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(mIntent);
            requireActivity().finish();
        }
    }

    private void initUI() {

        callbackManager = CallbackManager.Factory.create();
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();


    }

    private void onClickListenerBinding() {

        binding.setWelcomeCallback(new WelcomeCallback() {
            @Override
            public void onSendCode() {

                if (((BaseApp) requireActivity().getApplication()).isInternetAvailable()) {

                    if (validation()) {
                        callOTPApi();
                    }

                }

            }

            @Override
            public void onFacebookLogin() {

                facebooklogin();

            }

            @Override
            public void onEmailLogin() {

                LoadLoginFragment();
            }

            @Override
            public void onTermCondition() {
                loadTermNCondition();
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {

        }
    }


    private boolean validation() {

        if (TextUtils.isEmpty(binding.edtPhoneNumber.getText().toString())) {


            Toasty.info(getActivity(), "Phone number field shouldn't be empty.").show();

            return false;
        }

        if (binding.edtPhoneNumber.getText().toString().length() < 10) {

            Toasty.info(getActivity(), "Please enter valid mobile number").show();

            return false;
        } else {
            phoneNumber = binding.edtPhoneNumber.getText().toString();
        }


        return true;
    }

    public void callOTPApi() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.SendOtp.getValue());
        params.put("phone_no", phoneNumber);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        viewModel.sendOtp(params).observe(getActivity(), new Observer<WelcomeModel>() {
            @Override
            public void onChanged(WelcomeModel welcomeModel) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        if (welcomeModel != null) {
                            if (welcomeModel.getStatus() == 1) {
                                if (welcomeModel.getIsRegister() == 0) {
                                    loadRegisterFragment();
                                } else if (welcomeModel.getIsRegister() == 1) {
                                    otp = String.valueOf(welcomeModel.getOtp());
                                    LoadOtpFragment();
                                }
                            } else {
                                Toasty.error(getActivity(), welcomeModel.getMessage()).show();
                            }
                        } else {
                            Toasty.error(getActivity(), "Oops Something went wrong").show();
                        }
                    }
                }, 500);
            }
        });
    }

    private void loadRegisterFragment() {

        Bundle argumnets = new Bundle();
        argumnets.putInt("status", 0);
        argumnets.putString("mobile_no", binding.edtPhoneNumber.getText().toString());

        Fragment fragment = new RegisterFragment();
        fragment.setArguments(argumnets);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void LoadOtpFragment() {

        Bundle args = new Bundle();
        args.putString("mobile_no", binding.edtPhoneNumber.getText().toString());
        args.putString("otp", otp);


        Fragment fragment = new OtpVerificationFragment();
        fragment.setArguments(args);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void LoadLoginFragment() {

        Bundle argumnets = new Bundle();
        Fragment fragment = new LoginFragment();
        fragment.setArguments(argumnets);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void loadTermNCondition() {

        Bundle argumnets = new Bundle();
        argumnets.putString("TNS", "1");
        Fragment fragment = new TermAndConditionFragment();
        fragment.setArguments(argumnets);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void facebooklogin() {

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getUserDetail(loginResult);
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.e("check1", exception.getMessage());
                        if (exception instanceof FacebookAuthorizationException) {
                            if (AccessToken.getCurrentAccessToken() != null) {
                                LoginManager.getInstance().logOut();
                            }
                        }

                    }
                });

        LoginManager.getInstance().logInWithReadPermissions(WelcomeFragment.this, Arrays.asList("email"));

    }


    private void getUserDetail(LoginResult loginResult) {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        Log.e("User Data", json_object.toString());
                        String json = json_object.toString();
                        try {


                            JSONObject profile = new JSONObject(json);
                            name = profile.getString("name");
                            name = name.replace("%20", " ");
                            regId = profile.getString("id");
                            email = profile.getString("email");

                            if (progressDialog.isShowing()) {
                                progressDialog.hide();
                            }

                            getlogin(email, "");

                        } catch (JSONException e) {

                            if (progressDialog.isShowing()) {
                                progressDialog.hide();
                            }
//                            Toast.makeText(getActivity(), getString(R.string.email_error), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

//                            GeneralUtils.noConnection(getActivity(), "e.getMessage()");


                            GeneralUtils.noConnection(requireActivity(), requireActivity().getSupportFragmentManager(), " Facebook : " + e.getMessage());

                        }
                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

    private void getlogin(String email, String s) {

        Map<String, String> params = new HashMap<>();
        params.put("fb_google_id", regId);
        params.put("email", email);
        params.put("name", name);
        params.put("device_id", generalUtils.id(getActivity()));

        DebugLog.e("params => " + params.toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        viewModel.fbSocialLogin(params).observe(getActivity(), new Observer<FacebookResponseModel>() {
            @Override
            public void onChanged(FacebookResponseModel facebookResponseModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {


                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (facebookResponseModel != null) {
                                if (facebookResponseModel.getStatus() == 1) {


                                    Toasty.success(getActivity(), facebookResponseModel.getMessage()).show();


                                    String json = new Gson().toJson(facebookResponseModel.getData());
                                    new AppSharedPreference(getActivity()).addUserData(json);

                                    updateUserData();

//                                    new AppSharedPreference(getActivity()).setLogin(true);
//
//                                    Intent mIntent = new Intent(getActivity(), MainActivity.class);
//                                    startActivity(mIntent);
//                                    requireActivity().finish();

                                } else {

                                    Toasty.error(getActivity(), facebookResponseModel.getMessage()).show();


                                }
                            } else {

                                Toasty.error(getActivity(), "Oops Something went wrong").show();

                            }


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 300);

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateUserData() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.ProfileDetail.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        viewModel.getUserData(params).observe(getActivity(), new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin userModelLogin) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (userModelLogin != null) {
                            if (userModelLogin.getStatus() == 1) {

                                String jsonData = new Gson().toJson(userModelLogin.getData());
                                new AppSharedPreference(getActivity()).addUserData(jsonData);
                                new AppSharedPreference(getActivity()).saveHomeScreenData(null);
                                new AppSharedPreference(getActivity()).setLogin(true);

                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                getActivity().finish();

                            }
                        }

                    }
                }, 500);

            }
        });

    }

    private void getFCMToken() {

//        try {
//
//            FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>() {
//                @Override
//                public void onComplete(@NonNull Task<InstallationTokenResult> task) {
//
//                    String token = task.getResult().getToken();
//                    DebugLog.e("Token : " + token);
//                    new AppSharedPreference(getActivity()).saveFcmToken(token);
//
//                }
//            });
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//

    }

}