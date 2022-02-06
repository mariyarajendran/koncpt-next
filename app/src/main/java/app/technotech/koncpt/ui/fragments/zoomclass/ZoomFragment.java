package app.technotech.koncpt.ui.fragments.zoomclass;

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
import app.technotech.koncpt.databinding.FragmentZoomBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.zoomadapter.ZoomTabAdapter;

public class ZoomFragment extends Fragment {
    private FragmentZoomBinding binding;
    private ZoomTabAdapter zoomTabAdapter;
    private String mSubjectId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSubjectId = getArguments().getString("subject_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /// Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_zoom, container, false);
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
        zoomTabAdapter = new ZoomTabAdapter(getChildFragmentManager(), binding.tabLayout.getTabCount(), mSubjectId);
        zoomTabAdapter.add(getResources().getString(R.string.title_zoom_live_class_tab));
        zoomTabAdapter.add(getResources().getString(R.string.title_zoom_completed_class_tab));
        binding.viewPager.setAdapter(zoomTabAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

}
