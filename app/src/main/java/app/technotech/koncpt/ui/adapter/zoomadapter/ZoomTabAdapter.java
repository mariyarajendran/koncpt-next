package app.technotech.koncpt.ui.adapter.zoomadapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.ui.fragments.zoomclass.CompletedZoomFragment;
import app.technotech.koncpt.ui.fragments.zoomclass.OnLiveClassFragment;

public class ZoomTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String id;
    private final List<String> fragmentTitle = new ArrayList<>();

    public ZoomTabAdapter(FragmentManager fm, int NoOfTabs, String id) {
        super(fm);
        this.mNumOfTabs = NoOfTabs;
        this.id = id;
    }

    public void add(String title) {
        fragmentTitle.add(title);
    }

    @Override
    public int getCount() {
        return fragmentTitle.size();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OnLiveClassFragment.getInstance(id);
            case 1:
                return CompletedZoomFragment.getInstance(id);
            default:
                return null;
        }
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    @Override
    public int getItemPosition(@NonNull @NotNull Object object) {
        return POSITION_NONE;
    }
}
