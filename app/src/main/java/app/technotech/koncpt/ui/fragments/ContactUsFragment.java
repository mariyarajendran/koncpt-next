package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.ContactusModel;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.databinding.FragmentContactUsBinding;
import app.technotech.koncpt.ui.viewmodels.ContactusViewModel;
import app.technotech.koncpt.ui.viewmodels.FaqViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class ContactUsFragment extends Fragment {

    FragmentContactUsBinding binding;
    private ContactusViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    String name="",email="",query="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_us, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(ContactusViewModel.class);
        binding.setContactusViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=binding.editName.getText().toString().trim();
                email=binding.emailId.getText().toString().trim();
                query=binding.query.getText().toString().trim();
                if((name.matches("")))
                {
                    binding.editName.setError("Enter Name");
                }
                else if(email.matches(""))
                {
                    binding.editName.setError("Enter Email");
                }
                else if(query.matches(""))
                {
                    binding.query.setError("Enter Query");
                }
                else
                {
                    submitQuery(name,email,query);
                }

            }
        });
    }

    private void submitQuery(String name, String email, String query) {
        Map<String, String> params = new HashMap<>();
        params.put("name",name);
        params.put("email",email);
        params.put("query",query);

        DebugLog.e("params ContactUs==> " + params.toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getContactUsModel(params).observe(getActivity(), new Observer<ContactusModel>() {
            @Override
            public void onChanged(ContactusModel completeModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (completeModel != null) {
                            if (completeModel.getStatus() == 1) {
                                String json = new Gson().toJson(completeModel);
                                Log.d("json",json+"");
                                Toasty.info(getContext(),"We will contact  you soon").show();
                            } else {
                                Toasty.error(getActivity(), completeModel.getMessage()).show();

                            }
                        }

                    }
                }, 500);
            }
        });



    }
}