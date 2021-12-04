package app.technotech.koncpt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.ActivitySubjectDetailBinding;
import app.technotech.koncpt.ui.adapter.viewpageradapter.SubjectDetailsViewPagerAdapter;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.SubjectDetailsFragment;

public class SubjectDetailActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    private ActivitySubjectDetailBinding binding;
    private SubjectDetailsViewPagerAdapter mPagerAdapter;

    public static String subjectId ;
    public static String subjectTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SubjectDetailActivity.this, R.layout.activity_subject_detail);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.toolbar);

        Intent getdata = getIntent();

        if (getdata != null){
            subjectId = getdata.getStringExtra("subject_id");
            subjectTitle = getdata.getStringExtra("subject_name");
        }

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getSupportFragmentManager().findFragmentById(R.id.container) != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.container))).commit();
                } else {
                    onBackPressed();
                }


//                int count = getSupportFragmentManager().getBackStackEntryCount();
//                Toast.makeText(SubjectDetailActivity.this, "Count : " + count, Toast.LENGTH_SHORT).show();


            }
        });


        setTabLayout();

        getSupportActionBar().setTitle(subjectTitle);

//        LoadLoginFragment();
//        loadFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);

        return true;
    }


    private void LoadLoginFragment() {

        Bundle argumnets = new Bundle();
        Fragment fragment = new SubjectDetailsFragment();
        fragment.setArguments(argumnets);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void setToolBarTitle(String toolBarTitle) {
        binding.toolbar.setTitle(toolBarTitle);
    }


    private void loadFragment() {

        Fragment fragment;
        fragment = new SubjectDetailsFragment();

        if (getSupportFragmentManager().findFragmentById(R.id.container) != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.container))).commit();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }

    private void setTabLayout() {

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_all)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_paused)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_completed)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_unattempted)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_free)));

        mPagerAdapter = new SubjectDetailsViewPagerAdapter(getApplicationContext(), getSupportFragmentManager(), binding.tabLayout.getTabCount(), subjectId, subjectTitle);
        binding.viewPager.setAdapter(mPagerAdapter);
        binding.tabLayout.addOnTabSelectedListener(this);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        binding.viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}