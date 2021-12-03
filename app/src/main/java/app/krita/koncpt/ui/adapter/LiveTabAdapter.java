package app.technotech.koncpt.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import app.technotech.koncpt.ui.fragments.liveclass.AllFragment;
import app.technotech.koncpt.ui.fragments.liveclass.CompletedFragment;
import app.technotech.koncpt.ui.fragments.liveclass.FreeFragment;
import app.technotech.koncpt.ui.fragments.liveclass.PausedFragment;
import app.technotech.koncpt.ui.fragments.liveclass.UnattemptedFragment;


public class LiveTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String id;
    public LiveTabAdapter(FragmentManager fm, int NoofTabs, String id){
        super(fm);
        this.mNumOfTabs = NoofTabs;
        this.id = id;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                AllFragment home =  AllFragment.getInstance(id);
                return home;
            case 1:
                PausedFragment about = PausedFragment.getInstance(id);
                return about;
            case 2:
                CompletedFragment contact =  CompletedFragment.getInstance(id);
                return contact;
            case 3:
                UnattemptedFragment unattempted=UnattemptedFragment.getInstance(id);
                return unattempted;
            case 4:
                FreeFragment freeFragment= FreeFragment.getInstance(id);
                return freeFragment;
            default:
                return null;
        }
    }
}