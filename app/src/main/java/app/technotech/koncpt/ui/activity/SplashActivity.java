package app.technotech.koncpt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.databinding.ActivitySplashBinding;
import app.technotech.koncpt.ui.viewmodels.SplashViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    SplashViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(SplashViewModel.class);
        binding.setSplashViewModel(model);
        if (new AppSharedPreference(getApplicationContext()).isLogin()) {
            callApi();
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent redirect = new Intent(getApplicationContext(), UserAuthanticationActivity.class);
                    redirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(redirect);
                    finish();
                }
            }, 2000);
        }
    }

    private void callApi() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.ProfileDetail.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getApplicationContext()).getUserResponse().getId()));
        model.getUserData(params).observe(SplashActivity.this, new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin profileModel) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (profileModel != null) {
                            if (profileModel.getStatus() == 1) {
                                String jsonData = new Gson().toJson(profileModel.getData());
                                new AppSharedPreference(getApplicationContext()).addUserData(jsonData);
                                new AppSharedPreference(getApplicationContext()).saveHomeScreenData(null);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                }, 2000);
            }
        });
    }
}