package app.technotech.koncpt.ui.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentTestBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.viewpageradapter.TestViewPagerAdapter;


public class TestFragment extends Fragment implements TabLayout.OnTabSelectedListener {

//    @BindView(R.id.tabLayout)
//    TabLayout mTabLayout;
//
//    @BindView(R.id.viewPager)
//    ViewPager mViewPager;

    private TestViewPagerAdapter mPagerAdapter;
    private FragmentTestBinding binding;

    private View mView;
    private Context mContext;
    private BottomNavigationView bottomNavigationView;
    private MenuItem menuItem;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;


        setHasOptionsMenu(true);
        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();

        if (bottomNavigationView.getVisibility() == View.GONE) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
        setTabLayout();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);

        menuItem = menu.findItem(R.id.action_search);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Navigation.findNavController(mView).navigate(R.id.searchHomeFragment);
                return false;
            }
        });

        super.onPrepareOptionsMenu(menu);
    }

    private void setTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.test_tab_grand_test)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.test_tab_all_test)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.test_tab_mini_test)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.test_tab_subject_wise)));
        mPagerAdapter = new TestViewPagerAdapter(mContext, getChildFragmentManager(), binding.tabLayout.getTabCount(), mView);
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