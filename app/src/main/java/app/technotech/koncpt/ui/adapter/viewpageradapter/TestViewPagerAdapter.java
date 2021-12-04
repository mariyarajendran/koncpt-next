package app.technotech.koncpt.ui.adapter.viewpageradapter;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import app.technotech.koncpt.R;
import app.technotech.koncpt.ui.fragments.testTabs.AllTestFragment;
import app.technotech.koncpt.ui.fragments.testTabs.GrandTestFragment;
import app.technotech.koncpt.ui.fragments.testTabs.MiniTestFragment;
import app.technotech.koncpt.ui.fragments.testTabs.SubjectWiseTestFragment;

public class TestViewPagerAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    View mView;

    public TestViewPagerAdapter(Context context, FragmentManager fm,
                                int totalTabs, View mView) {

        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.mView = mView;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new GrandTestFragment();
//                return Navigation.findNavController(mView).navigate(R.id.action_customModuleFragment_to_customExamModuleFragment);
            case 1:
                return new AllTestFragment();
            case 2:
                return new MiniTestFragment();
            case 3:
                return new SubjectWiseTestFragment();

            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}