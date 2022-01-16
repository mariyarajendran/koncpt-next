package app.technotech.koncpt.ui.fragments.liveclass;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentSubjectBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.LiveTabAdapter;

public class SubjectFragment extends Fragment {
    private FragmentSubjectBinding binding;
    LiveTabAdapter mPagerAdapter;
    private String subject_id;
    FreeFragment freeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            subject_id = getArguments().getString("subject_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subject, container, false);
        binding.setLifecycleOwner(this);
        freeFragment = new FreeFragment();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }
        setTabLayout();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
    }

    private void setTabLayout() {
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_all)));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_paused)));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_completed)));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_unattempted)));
        //binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_details_tab_free)));
        mPagerAdapter = new LiveTabAdapter(getChildFragmentManager(), binding.tabLayout.getTabCount(), subject_id);
        mPagerAdapter.add(getResources().getString(R.string.subject_details_tab_all));
        mPagerAdapter.add(getResources().getString(R.string.subject_details_tab_paused));
        mPagerAdapter.add(getResources().getString(R.string.subject_details_tab_completed));
        mPagerAdapter.add(getResources().getString(R.string.subject_details_tab_unattempted));
        binding.viewPager.setAdapter(mPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}