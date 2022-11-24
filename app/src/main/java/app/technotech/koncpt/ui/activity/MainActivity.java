package app.technotech.koncpt.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.BuildConfig;
import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.ActivityMainBinding;
import app.technotech.koncpt.ui.fragments.main.DailyHuntFragment;
import app.technotech.koncpt.ui.fragments.main.HomeFragment;
import app.technotech.koncpt.ui.fragments.main.LiveClassesFragment;
import app.technotech.koncpt.ui.fragments.main.LiveClassesHomeFragment;
import app.technotech.koncpt.ui.fragments.main.QuestionBankFragment;
import app.technotech.koncpt.ui.fragments.main.QuestionBankLevelFragment;
import app.technotech.koncpt.ui.fragments.main.TestFragment;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;


public class MainActivity extends AppCompatActivity implements PaymentResultListener {


    CircularImageView headerImageView;
    TextView txt_student_name;
    TextView txt_student_field;

    private AppBarConfiguration mAppBarConfiguration;
    private NavController mNavController;
    private NavHostFragment mNavHostFragment;

    private ActivityMainBinding binding;

    passOnData passData;
    UpdateActivityResult activityResult;

    public static MainActivity instance;
    private AppUpdateManager appUpdateManager;


    private Long lastBackPressed = 10L;

    private Fragment homeFragment = new HomeFragment();
    private Fragment dailyHunt = new DailyHuntFragment();
    private Fragment qbankFragment = new QuestionBankFragment();
    private Fragment qbankLevelFragment = new QuestionBankLevelFragment();
    private Fragment testFragment = new TestFragment();
    private Fragment liveFragment = new LiveClassesFragment();
    private Fragment liveHomeFragment = new LiveClassesHomeFragment();
    public Fragment activeFragment = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.IS_LIVE_BUILD)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        initViews();
    }


    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void initViews() {
        instance = this;
        setSupportActionBar(binding.appBarMain.toolbar);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.questionBankLevelFragment,
                /*R.id.testFragment,*/ R.id.dailyHuntFragment,
                R.id.liveClassesHomeFragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        // mNavHostFragment
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        mNavController = mNavHostFragment.getNavController();
        View mNavigationViewHeaderView = binding.navView.getHeaderView(0);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, mNavController);
        NavigationUI.setupWithNavController(binding.appBarMain.toolbar, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.appBarMain.bottomNavigation, mNavController);
        headerImageView = (CircularImageView) mNavigationViewHeaderView.findViewById(R.id.headerImageView);
        txt_student_name = (TextView) mNavigationViewHeaderView.findViewById(R.id.txt_student_name);
        txt_student_field = (TextView) mNavigationViewHeaderView.findViewById(R.id.txt_student_field);
        final List<Integer> ids = new ArrayList<Integer>();
        ids.add(R.id.homeFragment);
        ids.add(R.id.dailyHuntFragment);
        ids.add(R.id.questionBankLevelFragment);
        /*ids.add(R.id.testFragment);*/
        ids.add(R.id.liveClassesHomeFragment);
        binding.appBarMain.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == mNavController.getCurrentBackStackEntry().getDestination().getId())
                    return false;
                for (Integer val : ids) {

                    if (val == item.getItemId()) {
                        mNavController.navigate(val, null, new NavOptions.Builder()
                                .setLaunchSingleTop(true)
                                .setPopUpTo(mNavController.getCurrentDestination().getId(), true)
                                .build());
                        return true;
                    }
                }
                return false;
            }
        });
        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (ids.indexOf(destination.getId()) != -1) {
                DebugLog.e("Destination IDs => " + destination.getId());
                mNavController.getGraph().setStartDestination(destination.getId());
            }
            if (destination.getId() == R.id.logoutFragment) {
                logoutData();
            }
        });
        txt_student_name.setText(new AppSharedPreference(getApplicationContext()).getUserResponse().getName());
        String[] degree = getResources().getStringArray(R.array.degree);
        if (!TextUtils.isEmpty(new AppSharedPreference(getApplicationContext()).getUserResponse().getCurrAcadYear())) {
            txt_student_field.setText(degree[Integer.parseInt(new AppSharedPreference(getApplicationContext()).getUserResponse().getCurrAcadYear())]);
        } else {
            txt_student_field.setText("");
        }
        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);

                    mNavController.navigate(R.id.profileFragment);

                }

            }
        });

    }

    private void logoutData() {
        new AppSharedPreference(getApplicationContext()).setLogin(false);
        new AppSharedPreference(MainActivity.this).clearAllData();

        Intent intent = new Intent(getApplicationContext(), UserAuthanticationActivity.class);
        startActivity(intent);
        finish();
    }

    private void setStatusBarColor(int Color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color);
        }

    }

    public void setDrawerLocked(boolean enabled) {
        if (enabled) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    public void setToolBarTitle(String toolBarTitle) {
        binding.appBarMain.toolbar.setTitle(toolBarTitle);
    }

    public void hideBottomNavigation() {
        binding.appBarMain.bottomNavigation.setVisibility(View.GONE);
    }

    public void showBottomNavigation() {
        binding.appBarMain.bottomNavigation.setVisibility(View.VISIBLE);
    }

    public BottomNavigationView getBottomNavigationView() {
        return binding.appBarMain.bottomNavigation;
    }

    public Toolbar getToolbar() {
        return binding.appBarMain.toolbar;
    }

    public NavController getmNavController() {
        return mNavController;
    }

    @Override
    public void onPaymentSuccess(String s) {

        try {
            passData.onSuccess(s);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void setSendData(passOnData sendData) {
        passData = sendData;
    }

    public void setActivityHandler(UpdateActivityResult result) {
        activityResult = result;
    }


    @Override
    public void onPaymentError(int i, String s) {

        try {
            passData.onFailure(i, s);
        } catch (Exception ex) {
            ex.printStackTrace();
            DebugLog.e("failure");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        activityResult.loadOnActivityResult(requestCode, resultCode, data);

    }

    public interface passOnData {
        void onSuccess(String s);

        void onFailure(int i, String s);
    }


    public interface UpdateActivityResult {
        void loadOnActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }


    public CircularImageView getCircularImage() {
        return headerImageView;
    }


    @Override
    public void onBackPressed() {
        if (mNavHostFragment.getChildFragmentManager().getBackStackEntryCount() == 0) {
            if (lastBackPressed + 1000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
                lastBackPressed = System.currentTimeMillis();
//                Toasty.info(getApplicationContext(), "Already Back Pressed").show();
            }
        } else {
            super.onBackPressed();
        }
    }


    public NavHostFragment getmNavHostFragment() {
        return mNavHostFragment;
    }

    @Override
    protected void onDestroy() {
        new AppSharedPreference(getApplicationContext()).saveHomeScreenData(null);
        super.onDestroy();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}