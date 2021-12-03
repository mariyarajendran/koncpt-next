package app.technotech.koncpt.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;
import java.util.Map;

import app.krita.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.WelcomeModel;
import app.technotech.koncpt.databinding.DialogLinkPhoneNoBinding;
import app.technotech.koncpt.ui.viewmodels.WelcomeViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class SendOTPDialogFragment extends DialogFragment {

    private DialogLinkPhoneNoBinding binding;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private AppSharedPreference sharedPreference;
    private WelcomeViewModel model;

    private String phoneNumber;
    private String otp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_link_phone_no, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(WelcomeViewModel.class);
        binding.setWelcomeViewModel(model);


        return binding.getRoot();

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialog);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
    }


    private boolean validation() {

        if (TextUtils.isEmpty(binding.mobileEt.getText().toString())) {
            Toasty.info(getActivity(), "Phone number field shouldn't be empty.").show();
            return false;
        }

        if (binding.mobileEt.getText().toString().length() < 10) {
            Toasty.info(getActivity(), "Please enter valid mobile number").show();
            return false;
        } else {
            phoneNumber = binding.mobileEt.getText().toString();
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
        model.sendOtp(params).observe(getActivity(), new Observer<WelcomeModel>() {
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
                                    Toasty.info(getActivity(), welcomeModel.getMessage()).show();
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

    private void LoadOtpFragment() {


    }

}
