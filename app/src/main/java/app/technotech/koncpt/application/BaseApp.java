package app.technotech.koncpt.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.testfairy.TestFairy;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;

import app.technotech.koncpt.data.DataRepository;
import app.technotech.koncpt.data.local.AppDatabase;
import app.technotech.koncpt.utils.AppConstants;
import app.technotech.koncpt.utils.AppExecutors;
import app.technotech.koncpt.zoom.AuthConstants;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;


public class BaseApp extends Application implements InternetConnectivityListener {

    private Boolean isInternetAvailable = false;
    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();


        registerActivityLifecycle();
        TestFairy.begin(this, "ac8b3c0e75d694545f6c937efbdc828d0c4d11da");

        initializeSdk(this);

        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);


        mInternetAvailabilityChecker = InternetAvailabilityChecker.init(this);
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);
        mAppExecutors = new AppExecutors();
    }

    public Boolean isInternetAvailable() {
        return isInternetAvailable;
    }

    public AppExecutors getAppExecutors() {
        return mAppExecutors;
    }

    public SharedPreferences getPreferences() {
        return this.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase(), getPreferences(), getAppExecutors());
    }


    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        this.isInternetAvailable = isConnected;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mInternetAvailabilityChecker.removeAllInternetConnectivityChangeListeners();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mInternetAvailabilityChecker.removeAllInternetConnectivityChangeListeners();
    }


    private void initializeSdk(Context context) {
        ZoomSDK zoomSDK = ZoomSDK.getInstance();
        ZoomSDKInitParams initParams = new ZoomSDKInitParams();
        initParams.appKey = AuthConstants.APP_KEY;
        initParams.appSecret = AuthConstants.APP_SECRET;
        initParams.domain = AuthConstants.WEB_DOMAIN;
        initParams.enableLog = true;
        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
            @Override
            public void onZoomSDKInitializeResult(int i, int i1) {

            }

            @Override
            public void onZoomAuthIdentityExpired() {

            }
        };

        zoomSDK.initialize(context, listener, initParams);
    }


    private void registerActivityLifecycle() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {


            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
               // activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }

            @Override 
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle
                    outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });

    }

}
