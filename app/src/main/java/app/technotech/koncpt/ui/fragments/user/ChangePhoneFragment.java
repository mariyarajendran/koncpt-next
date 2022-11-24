package app.technotech.koncpt.ui.fragments.user;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import app.technotech.koncpt.data.network.model.ChangePhoneModule;
import app.technotech.koncpt.databinding.FragmentChangeNumberBinding;
import app.technotech.koncpt.ui.viewmodels.ChangePhoneViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class ChangePhoneFragment extends Fragment {

    FragmentChangeNumberBinding binding;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private ChangePhoneViewModel model;
    String phoneNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
// doneokkk
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_number, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(ChangePhoneViewModel.class);
        binding.setChangeViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
                sendPost(phoneNumber);
            }
        });


    }

    private void sendPost(String phoneNumber) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("phone", phoneNumber);


        DebugLog.e("params All==> " + params.toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<ChangePhoneModule>() {
            @Override
            public void onChanged(ChangePhoneModule completeModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (completeModel != null) {
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

    private void loadData(ChangePhoneModule completeModel) {
        if (completeModel.getMessage().equals("Phone updated successfully")) {
            Toast.makeText(getContext(), "Phone updated successfully...!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Phone updated successfully...!", Toast.LENGTH_LONG).show();

        }
    }

}
