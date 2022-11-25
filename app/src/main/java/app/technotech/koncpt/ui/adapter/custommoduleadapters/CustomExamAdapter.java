package app.technotech.koncpt.ui.adapter.custommoduleadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.ui.fragments.qbank.custommodule.CustomQuestionFragment;

public class CustomExamAdapter extends FragmentStatePagerAdapter {


    private List<CustomExamModel.Datum> datumList = new ArrayList<>();

    public CustomExamAdapter(@NonNull FragmentManager fm, int behavior, List<CustomExamModel.Datum> datumList) {
        super(fm, behavior);
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CustomQuestionFragment.getInstance(datumList.get(position));
    }

    @Override
    public int getCount() {
        return datumList.size();
    }
}
