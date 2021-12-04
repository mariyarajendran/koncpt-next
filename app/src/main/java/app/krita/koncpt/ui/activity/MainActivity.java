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

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.ActivityMainBinding;
import app.technotech.koncpt.ui.fragments.main.DailyHuntFragment;
import app.technotech.koncpt.ui.fragments.main.HomeFragment;
import app.technotech.koncpt.ui.fragments.main.LiveClassesFragment;
import app.technotech.koncpt.ui.fragments.main.QuestionBankFragment;
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
    private Fragment testFragment = new TestFragment();
    private Fragment liveFragment = new LiveClassesFragment();
    public Fragment activeFragment = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                R.id.homeFragment, R.id.questionBankFragment,
                R.id.testFragment, R.id.dailyHuntFragment,
                R.id.liveClassesFragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();


//        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, R.id.homeFragment, false).commit();


        // mNavHostFragment
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        mNavController = mNavHostFragment.getNavController();

//        mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph())
//                .setOpenableLayout(mMainDrawerLayout)
//                .build();


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
        ids.add(R.id.questionBankFragment);
        ids.add(R.id.testFragment);
        ids.add(R.id.liveClassesFragment);

//        ids.forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//
//            }
//        });

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
//                    switch (item.getItemId()) {
//
//                        case R.id.homeFragment:
//                            getSupportFragmentManager().beginTransaction().hide(activeFragment).show(homeFragment).commit();
//                            activeFragment = homeFragment;
//                            return true;
//
//                        case R.id.dailyHuntFragment:
//                            getSupportFragmentManager().beginTransaction().hide(activeFragment).show(dailyHunt).commit();
//                            activeFragment = dailyHunt;
//                            return true;
//
//                        case R.id.questionBankFragment:
//                            getSupportFragmentManager().beginTransaction().hide(activeFragment).show(qbankFragment).commit();
//                            activeFragment = qbankFragment;
//                            return true;
//
//                        case R.id.testFragment:
//                            getSupportFragmentManager().beginTransaction().hide(activeFragment).show(testFragment).commit();
//                            activeFragment = testFragment;
//                            return true;
//
//                        case R.id.liveClassesFragment:
//                            getSupportFragmentManager().beginTransaction().hide(activeFragment).show(liveFragment).commit();
//                            activeFragment = liveFragment;
//                            return true;
//                    }
//
//                    return false;
//                }


//                switch (item.getItemId()) {
//
//                    case R.id.homeFragment:
//                        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(homeFragment).commit();
//                        activeFragment = homeFragment;
//                        return true;
//
//                    case R.id.dailyHuntFragment:
//                        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(dailyHunt).commit();
//                        activeFragment = dailyHunt;
//                        return true;
//                    case R.id.questionBankFragment:
//                        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(qbankFragment).commit();
//                        activeFragment = qbankFragment;
//                        return true;
//
//                    case R.id.testFragment:
//                        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(testFragment).commit();
//                        activeFragment = testFragment;
//                        return true;
//
//                    case R.id.liveClassesFragment:
//                        getSupportFragmentManager().beginTransaction().hide(activeFragment).show(liveFragment).commit();
//                        activeFragment = liveFragment;
//                        return true;
//
//                }
//
                return false;
//            }
            }

        });


        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {


            if (ids.indexOf(destination.getId()) != -1) {
//                mNavController.getGraph().getStartDestination() = destination.getId();
                DebugLog.e("Destination IDs => " + destination.getId());
                mNavController.getGraph().setStartDestination(destination.getId());
            }


            if (destination.getId() == R.id.logoutFragment) {
                logoutData();
            }

//            switch (destination.getId()) {
//
//
//
//                case R.id.action_homeFragment_To_grandExamTest:
//                    mMainToolbar.setVisibility(View.VISIBLE);
//                    mBottomNavigationView.setVisibility(View.GONE);
//                    setDrawerLocked(true);
//                    break;
//
//                case R.id.homeFragment:
//                    mMainToolbar.setVisibility(View.VISIBLE);
//                    mBottomNavigationView.setVisibility(View.VISIBLE);
//                    setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//                    setDrawerLocked(false);
//                    break;
//
//                case R.id.questionBankFragment:
//                    mMainToolbar.setVisibility(View.VISIBLE);
//                    mBottomNavigationView.setVisibility(View.VISIBLE);
//                    setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//                    setDrawerLocked(false);
//                    break;
//
//                case R.id.testFragment:
//
//                    mMainToolbar.setVisibility(View.VISIBLE);
//                    mBottomNavigationView.setVisibility(View.VISIBLE);
//                    setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//                    setDrawerLocked(false);
//                    break;
//
//
//                case R.id.welcomeFragment:
//                case R.id.registerFragment:
//                case R.id.otpVerificationFragment:
//                case R.id.forgotPasswordFragment:
//                case R.id.loginFragment:
//                    mMainToolbar.setVisibility(View.GONE);
//                    mBottomNavigationView.setVisibility(View.GONE);
//                    setStatusBarColor(Color.WHITE);
//                    setDrawerLocked(true);
//                    break;
//
//                case R.id.allClassFragment:
//                case R.id.videoFragment:
//                case R.id.profileFragment:
//                case R.id.reviewQuestionsFragment:
//                case R.id.grandExamTest:
//                case R.id.resultExamFragment:
//                case R.id.subjectDetailsFragment:
//                case R.id.blookMarkFragment:
//                case R.id.customModuleFragment:
//                case R.id.createCustomModuleOneFragment:
//                case R.id.createCustomModuleTwoFragment:
//                    mBottomNavigationView.setVisibility(View.GONE);
//                    setDrawerLocked(true);
//                    break;
//                case R.id.logoutFragment:
////                    new AppSharedPreference(getActivity()).setLogin(true);
//                    new AppSharedPreference(MainActivity.this).clearAllData();
//
//                    Intent intent = new Intent(getApplicationContext(), UserAuthanticationActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//
//            }
        });


        txt_student_name.setText(new AppSharedPreference(getApplicationContext()).getUserResponse().getName());


        String[] degree = getResources().getStringArray(R.array.degree);

//        String acadmic_year = new AppSharedPreference(getApplicationContext()).getUserResponse().getCurrAcadYear();

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