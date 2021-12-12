package app.technotech.koncpt.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.ui.fragments.liveclass.AllFragment;
import app.technotech.koncpt.ui.fragments.liveclass.CompletedFragment;
import app.technotech.koncpt.ui.fragments.liveclass.PausedFragment;
import app.technotech.koncpt.ui.fragments.liveclass.UnattemptedFragment;


public class LiveTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String id;
    private final List<String> fragmentTitle = new ArrayList<>();

    public LiveTabAdapter(FragmentManager fm, int NoofTabs, String id) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
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
                return AllFragment.getInstance(id);
            case 1:
                return PausedFragment.getInstance(id);
            case 2:
                return CompletedFragment.getInstance(id);
            case 3:
                return UnattemptedFragment.getInstance(id);
//            case 4:
//                FreeFragment freeFragment= FreeFragment.getInstance(id);
//                return freeFragment;
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