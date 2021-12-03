package app.technotech.koncpt.ui.fragments.liveclass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentSubjectMainBinding;
import app.technotech.koncpt.ui.activity.MainActivity;

public class MainFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private FragmentSubjectMainBinding binding;
    private String video_id, topic_name;
    MainTabAdapter mPagerAdapter;

    // 1 minute

//    public SlidesFragment.OnSliderListener setSliderLisener(SlidesFragment.OnSliderListener listener){
//        this.listener = listener;
//    }

    public static MainFragment mInstnce;
    public static MainFragment getInstance() {
        return mInstnce;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInstnce = this;
        if (getArguments() != null) {
            video_id = getArguments().getString("video_id");
            Log.d("video_idMain", video_id + "");
            String one = getArguments().getString("one");
            topic_name = getArguments().getString("topic_name");
            Log.d("fragValue", one + "");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_subject_main, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setHasOptionsMenu(true);
        setTabLayout();

        ((MainActivity) requireActivity()).setToolBarTitle(topic_name);

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    private void setTabLayout() {

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_class)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_slides)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getResources().getString(R.string.subject_notes)));

        mPagerAdapter = new MainTabAdapter(getChildFragmentManager(), binding.tabLayout.getTabCount(), video_id);
        binding.viewPager.setAdapter(mPagerAdapter);
        binding.tabLayout.addOnTabSelectedListener(this);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
    }


    public void UpdateMyCount(String count, int tab) {
        switch (tab) {
            case 0:
                binding.tabLayout.getTabAt(0).setText(getResources().getString(R.string.subject_class) + " (" + count + ")");
                break;
            case 1:
                binding.tabLayout.getTabAt(1).setText(getResources().getString(R.string.subject_slides) + " (" + count + ")");
                break;
            case 2:
                binding.tabLayout.getTabAt(2).setText(getResources().getString(R.string.subject_notes));
                break;

        }


        //1 minn   it works :..>)but it shows 1st tab name...i have to update tab name at 0 and 2 position

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


    public TabLayout getTabLayoutView(){
        return binding.tabLayout;
    }


}
