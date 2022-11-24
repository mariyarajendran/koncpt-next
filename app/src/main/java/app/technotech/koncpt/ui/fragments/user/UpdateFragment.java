package app.technotech.koncpt.ui.fragments.user;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.UpdatePasswordModel;
import app.technotech.koncpt.databinding.FragmentUpdatePasswordBinding;
import app.technotech.koncpt.ui.viewmodels.UpdatePasswordViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class UpdateFragment extends Fragment {

    FragmentUpdatePasswordBinding binding;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private UpdatePasswordViewModel model;
    String oldPassword,newPassword,confirmPassword;
    boolean val= false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
// doneokkk
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_password, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(UpdatePasswordViewModel.class);
        binding.setUpdateViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        viewPassword();
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPassword=binding.edtOldPassword.getText().toString().trim();
                newPassword=binding.edtNewPassword.getText().toString().trim();
                confirmPassword=binding.edtConfirmPassword.getText().toString().trim();
                if(newPassword.equals(confirmPassword))
                {
                    sendPost(oldPassword,newPassword,confirmPassword);
                }
                else
                {
                    Toast.makeText(getContext(),"New Password &amp; Confirm Password should be same",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    private void viewPassword() {
        binding.txtShowOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val==false)
                {
                    binding.edtOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.txtShowOne.setText("hide");
                    val=true;
                }else if(val==true)
                {
                    binding.edtOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.txtShowOne.setText("show");
                    val=false;
                }
            }
        });


        binding.txtShowTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val==false)
                {
                    binding.edtNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.txtShowTwo.setText("hide");
                    val=true;
                }else if(val==true)
                {
                    binding.edtNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.txtShowTwo.setText("show");
                    val=false;
                }
            }
        });


        binding.txtShowThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val==false)
                {
                    binding.edtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.txtShowThree.setText("hide");
                    val=true;
                }else if(val==true)
                {
                    binding.edtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.txtShowThree.setText("show");
                    val=false;
                }
            }
        });



    }

    private void sendPost(String oldPassword, String newPassword, String confirmPassword) {
;
        Map<String, String> params = new HashMap<>();
    params.put("user_id",Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("password_confirmation",confirmPassword);
        params.put("password",newPassword);
        params.put("oldPassword",oldPassword);


        DebugLog.e("params All==> " + params.toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<UpdatePasswordModel>() {
            @Override
            public void onChanged(UpdatePasswordModel completeModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (completeModel != null) {
                            String json1 = new Gson().toJson(completeModel);
                            DebugLog.e("Json ==> " + json1);

                            if (completeModel.getStatus() == 1) {
                                String json = new Gson().toJson(completeModel);
                                DebugLog.e("Json ==> " + json);

                                loadData(completeModel);
                                // Toasty.success(getActivity(), completeModel.getMessage()).show();

                            } else {
                                Toasty.error(getActivity(), completeModel.getMessage()).show();

                            }
                        }

                    }
                }, 500);
            }
        });


    }

    private void loadData(UpdatePasswordModel completeModel) {
        if(completeModel.getMessage().equals("Password updated successfully"))
        {
            Toast.makeText(getContext(),"Password updated successfully...!",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_LONG).show();

        }
    }

}
