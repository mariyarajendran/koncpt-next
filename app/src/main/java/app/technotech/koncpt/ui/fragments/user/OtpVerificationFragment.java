package app.technotech.koncpt.ui.fragments.user;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.mukesh.OnOtpCompletionListener;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.data.network.model.WelcomeModel;
import app.technotech.koncpt.databinding.FragmentOtpVerificationBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.OtpCallback;
import app.technotech.koncpt.ui.viewmodels.OtpViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

@SuppressWarnings("ALL")
public class OtpVerificationFragment extends Fragment implements OnOtpCompletionListener {


    private FragmentOtpVerificationBinding binding;
    private OtpViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private AppSharedPreference sharedPreference;


    private String otpCode;
    private String phone_no;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            phone_no = bundle.getString("mobile_no");
            otpCode = bundle.getString("otp");
            DebugLog.e("OTP is " + otpCode);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp_verification, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(OtpViewModel.class);
        binding.setOtpViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniUI();
        bindClickListener();
        estimateTimerCountdown();


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
                            DebugLog.e("Token : " + token);
                            new AppSharedPreference(getActivity()).saveFcmToken(token);
                        }
                    });


        }

    }

    private void bindClickListener() {

        binding.setOtpCallbacks(new OtpCallback() {
            @Override
            public void onBackPressed() {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }

            @Override
            public void onResendOTP() {

                if (binding.txtCount.getText().toString().equals("Resend OTP")) {
                    resendOTP();
                }

            }

            @Override
            public void onValidateOTP() {

                if (otpCode.equals(binding.otpView.getText().toString())) {

                    verifyApiCall();

                } else {

                    Toasty.error(getActivity(), "Otp is not matched").show();


                }


            }
        });

    }


    private void iniUI() {


        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();

        binding.otpView.setOtpCompletionListener(this);
        //  Toast.makeText(getActivity(), "OTP IS : " + otpCode, Toast.LENGTH_SHORT).show();

    }


    private void estimateTimerCountdown() {


        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                binding.txtCount.setText(Html.fromHtml("<font color=##515C6F> Resend : </font> <font color=##515C6F>" + (millisUntilFinished / 1000) + " </font>"));
            }

            public void onFinish() {
                binding.txtCount.setText(Html.fromHtml("<font color=##515C6F>Resend OTP</font>"));
            }

        }.start();

    }


    @Override
    public void onOtpCompleted(String otp) {

        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {


            }
        });


    }


    private void verifyApiCall() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.VerifyOtp.getValue());
        params.put("otp", otpCode);
        params.put("phone_no", phone_no);
        params.put("device_id", new AppSharedPreference(getActivity()).getSavedToken());
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.verifyOTP(params).observe(getActivity(), new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin userModelLogin) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }


                            if (userModelLogin != null) {

                                if (userModelLogin.getStatus() == 1) {

                                    String jsonData = new Gson().toJson(userModelLogin.getData());
                                    new AppSharedPreference(getActivity()).addUserData(jsonData);

                                    updateUserData();

                                } else {

                                    Toasty.error(getActivity(), userModelLogin.getMessage()).show();


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


    private void resendOTP() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.ResendOtp.getValue());
        params.put("phone_no", phone_no);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.reSendOtp(params).observe(getActivity(), new Observer<WelcomeModel>() {
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
                                Toasty.success(getActivity(), "Resend OTP Successfully").show();
                                otpCode = Integer.toString(welcomeModel.getOtp());
                            }
                        }

                    }
                }, 500);

            }
        });
    }
}