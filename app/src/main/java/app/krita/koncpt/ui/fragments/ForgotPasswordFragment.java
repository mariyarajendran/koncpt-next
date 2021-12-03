package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.databinding.FragmentForgotPasswordBinding;
import app.technotech.koncpt.ui.viewmodels.ForgetPasswordViewModel;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class ForgotPasswordFragment extends Fragment {


    private FragmentForgotPasswordBinding binding;
    private ForgetPasswordViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private Context mContext;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(ForgetPasswordViewModel.class);
        binding.setForgetModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        utils = new GeneralUtils(mContext);
        progressDialog = utils.showProgressDialog();


        binding.btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                    callApi();
                }


            }
        });



        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });

    }

    private void callApi() {

        Map<String, String> params = new HashMap<>();
        params.put("email", binding.edtEmailId.getText().toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.sendPasswordMail(params).observe(getActivity(), new Observer<MessageModel>() {
            @Override
            public void onChanged(MessageModel messageModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {


                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }

                            if (messageModel != null) {
                                if (messageModel.getStatus() == 1) {
                                    Toasty.success(mContext, messageModel.getMessage()).show();

                                    if (getFragmentManager() != null) {
                                        getFragmentManager().popBackStackImmediate();
                                    }

                                } else {
                                    Toasty.error(mContext, messageModel.getMessage()).show();
                                }

                            } else {

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });


    }

    private boolean validation() {

        if (TextUtils.isEmpty(binding.edtEmailId.getText().toString())) {
            Toasty.info(mContext, "This field shouldn't be empty").show();
            return false;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmailId.getText().toString()).matches()) {
            Toasty.info(mContext, "please enter valid email address").show();
            return false;
        }

        return true;

    }
}