package app.technotech.koncpt.ui.activity;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.databinding.ActivitySplashBinding;
import app.technotech.koncpt.ui.viewmodels.SplashViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import es.dmoral.toasty.Toasty;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    SplashViewModel model;
    final static int REQUEST_CODE_IN_APP_UPDATE = 108;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(SplashViewModel.class);
        binding.setSplashViewModel(model);
        redirectToAuthWithHomeScreen();
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

    private void redirectToAuthWithHomeScreen() {
        if (new AppSharedPreference(getApplicationContext()).isLogin()) {
            callApi();
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent redirect = new Intent(getApplicationContext(), UserAuthanticationActivity.class);
                redirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(redirect);
                finish();
            }, 2000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initInAppUpdatePlayStore();
    }

    private void initInAppUpdatePlayStore() {
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            IMMEDIATE,
                            this,
                            REQUEST_CODE_IN_APP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IN_APP_UPDATE) {
            Toasty.success(getApplicationContext(), getResources().getString(R.string.msg_start_download)).show();
        }
        if (resultCode != RESULT_OK) {
            redirectToAuthWithHomeScreen();
        }
    }
}