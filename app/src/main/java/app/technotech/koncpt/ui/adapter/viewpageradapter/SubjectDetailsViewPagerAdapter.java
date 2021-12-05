package app.technotech.koncpt.ui.adapter.viewpageradapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.AllClassFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.CompleteClassesFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.FreeClassesFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.PauseClassesFragment;
import app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs.UnAttemptedClassesFragment;

public class SubjectDetailsViewPagerAdapter extends FragmentPagerAdapter {
    private Context myContext;
    int totalTabs;
    String subjectId;
    String subjectTitle;
    String levelId;

    public SubjectDetailsViewPagerAdapter(Context context, FragmentManager fm, int totalTabs, String subjectId, String subjectTitle, String levelId) {
        super(fm);
        this.subjectId = subjectId;
        this.subjectTitle = subjectTitle;
        this.levelId = levelId;
        myContext = context;
        this.totalTabs = totalTabs;
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
        return totalTabs;
    }
}