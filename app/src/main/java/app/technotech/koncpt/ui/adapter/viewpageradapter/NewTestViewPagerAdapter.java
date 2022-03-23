package app.technotech.koncpt.ui.adapter.viewpageradapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.AllClassFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.CompleteClassesFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.PauseClassesFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.UnAttemptedClassesFragment;

public class NewTestViewPagerAdapter extends FragmentStatePagerAdapter {
    int totalTabs;
    String subjectId;
    String subjectTitle;
    String levelId;
    private final List<String> fragmentTitle = new ArrayList<>();

    public NewTestViewPagerAdapter(Context context, FragmentManager fm, int totalTabs, String subjectId, String subjectTitle, String levelId) {
        super(fm);
        this.subjectId = subjectId;
        this.subjectTitle = subjectTitle;
        this.levelId = levelId;
        this.totalTabs = totalTabs;
    }

    public void add(String title) {
        fragmentTitle.add(title);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AllClassFragment.getInstance(subjectId, subjectTitle, levelId);
            case 1:
                return PauseClassesFragment.getInstance(subjectId, subjectTitle, levelId);
            case 2:
                return CompleteClassesFragment.getInstance(subjectId, subjectTitle, levelId);
            case 3:
                return UnAttemptedClassesFragment.getInstance(subjectId, subjectTitle, levelId);
            /*case 4:
                return FreeClassesFragment.getInstance(subjectId, subjectTitle, levelId);*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragmentTitle.size();
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