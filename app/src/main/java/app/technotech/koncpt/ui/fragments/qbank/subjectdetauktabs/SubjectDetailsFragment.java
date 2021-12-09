package app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentTestDetailsBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.viewpageradapter.SubjectDetailsViewPagerAdapter;


public class SubjectDetailsFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private FragmentTestDetailsBinding binding;
    private SubjectDetailsViewPagerAdapter mPagerAdapter;
    private View mView;
    private Context mContext;
    public static String subjectId;
    public static String subjectTitle;
    public static String levelId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subjectId = getArguments().getString("subject_id");
            subjectTitle = getArguments().getString("subject_name");
            levelId = getArguments().getString("level_id");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_test_details, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;
        setHasOptionsMenu(true);
        setTabLayout();
        ((MainActivity) requireActivity()).setToolBarTitle(subjectTitle);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_all)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_paused)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_completed)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_unattempted)));
        /*binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_free)));*/
        mPagerAdapter = new SubjectDetailsViewPagerAdapter(getActivity(), getChildFragmentManager(), binding.tabLayout.getTabCount(), subjectId, subjectTitle, levelId);
        binding.viewPager.setAdapter(mPagerAdapter);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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