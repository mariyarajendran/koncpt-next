package app.technotech.koncpt.ui.fragments.user;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.FacebookResponseModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.UserModelLogin2;
import app.technotech.koncpt.databinding.FragmentLoginBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.LoginCallback;
import app.technotech.koncpt.ui.fragments.ForgotPasswordFragment;
import app.technotech.koncpt.ui.fragments.TermAndConditionFragment;
import app.technotech.koncpt.ui.viewmodels.LoginViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private LoginViewModel model;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        binding.setLoginViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        callbackManager = CallbackManager.Factory.create();

        //  validation();

        DebugLog.e("Token : " + new AppSharedPreference(getActivity()).getSavedToken());

        if (new AppSharedPreference(getActivity()).getSavedToken() == null) {
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                                return;
                            }

                            // Get new FCM registration token
                            String token = task.getResult();
                            new AppSharedPreference(getActivity()).saveFcmToken(token);
                        }
                    });
        }

        DebugLog.e("Token : " + new AppSharedPreference(getActivity()).getSavedToken());


        buttonClickListener();

    }

    private void buttonClickListener() {

        binding.setLoginCallback(new LoginCallback() {
            @Override
            public void onLogin() {

                if (validation()) {

                    ApiCallLogin();
                }

            }

            @Override
            public void onFBLogin() {
                facebooklogin();
            }

            @Override
            public void onSignUp() {
                LoadRegisterFragment();
            }

            @Override
            public void onForgotPassword() {

                Bundle argumnets = new Bundle();
                Fragment fragment = new ForgotPasswordFragment();
                fragment.setArguments(argumnets);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onTermCondition() {
                loadTermNCondition();
            }
        });

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


    private void ApiCallLogin() {
        Map<String, String> params = new HashMap<>();
        params.put("email", binding.edtEmail.getText().toString());
        params.put("password", binding.edtPassword.getText().toString());
        params.put("device_id", new AppSharedPreference(getActivity()).getSavedToken());
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.SignInWithPassword(params).observe(getActivity(), new Observer<UserModelLogin2>() {
            @Override
            public void onChanged(UserModelLogin2 userModelLogin) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }


                            if (userModelLogin != null) {

                                if (userModelLogin.getStatus() == 1) {
                                    String jsonDAta = new Gson().toJson(userModelLogin);
                                    DebugLog.e("JOSN => " + jsonDAta);

                                    Toasty.success(getActivity(), userModelLogin.getMessage()).show();

                                    UserModelLogin.Data data = new UserModelLogin.Data();
                                    data.setPlan(userModelLogin.getData().getPlan());
                                    data.setSubscriptionEndsAt(userModelLogin.getData().getSubscriptionEndsAt());
                                    data.setFbGoogleId(userModelLogin.getData().getFbGoogleId());
                                    data.setName(userModelLogin.getData().getName());
                                    data.setEmail(userModelLogin.getData().getEmail());
                                    data.setId(userModelLogin.getData().getId());
                                    data.setCurrAcadYear(userModelLogin.getData().getCurrAcadYear());
                                    data.setStateId(userModelLogin.getData().getStateId());
                                    data.setLoginEnabled(userModelLogin.getData().getLoginEnabled());
                                    data.setPhone(userModelLogin.getData().getPhone());
                                    data.setAddress(userModelLogin.getData().getAddress());
                                    data.setStatename(userModelLogin.getData().getStatename());
                                    data.setDob(userModelLogin.getData().getDob());
                                    data.setCollegeName(userModelLogin.getData().getCollegeName());
                                    data.setGender(userModelLogin.getData().getGender());
                                    data.setCourse(userModelLogin.getData().getCourse());
                                    data.setImage(userModelLogin.getData().getImage());

                                    String json = new Gson().toJson(data);
                                    new AppSharedPreference(getActivity()).addUserData(json);

                                    updateUserData();

                                } else {
                                    Toasty.error(getActivity(), userModelLogin.getMessage()).show();

                                }

                            } else {

                                Toasty.success(getActivity(), "Oops Something went wrong").show();
                            }


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 300);

            }
        });


    }

    private boolean validation() {
        if (TextUtils.isEmpty(binding.edtEmail.getText().toString())) {


            Toasty.error(getActivity(), "This field shouldn't be empty").show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.getText().toString()).matches()) {


            Toasty.success(getActivity(), "please enter valid email address").show();
            return false;
        }

        if (TextUtils.isEmpty(binding.edtPassword.getText().toString())) {

            Toasty.error(getActivity(), "This field shouldn't be empty").show();
            return false;
        }

        return true;

    }

    private void LoadRegisterFragment() {


        Bundle argumnets = new Bundle();
        argumnets.putInt("status", 1);

        Fragment fragment = new RegisterFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void updateUserData() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.ProfileDetail.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getUserData(params).observe(getActivity(), new Observer<UserModelLogin>() {
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

        LoginManager.getInstance().logInWithReadPermissions(LoginFragment.this, Arrays.asList("email"));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
                            Toast.makeText(getActivity(), getString(R.string.email_error), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
        params.put("device_id", utils.id(getActivity()));


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.fbSocialLogin(params).observe(getActivity(), new Observer<FacebookResponseModel>() {
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

//                                    Sneaker.with(getActivity()) // Activity, Fragment or ViewGroup
//                                            .setTitle("Success!!")
//                                            .setMessage(facebookResponseModel.getMessage())
//                                            .sneakSuccess();

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
}