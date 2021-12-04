package app.technotech.koncpt.ui.fragments.liveclass;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class MainTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String id;

    public MainTabAdapter(FragmentManager fm, int NoofTabs, String id) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
        this.id = id;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ClassFragment home = ClassFragment.getInstance(id);
                return home;
            case 1:
                SlidesFragment contact = SlidesFragment.getInstance(id);
                return contact;
            case 2:
                NotesFragment about = NotesFragment.getInstance(id);
                return about;

            default:
                return null;
        }
    }

}
