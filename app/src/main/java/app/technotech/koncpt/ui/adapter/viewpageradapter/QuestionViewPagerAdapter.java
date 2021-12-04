package app.technotech.koncpt.ui.adapter.viewpageradapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import app.technotech.koncpt.QuestionFragment;
import app.technotech.koncpt.data.local.entites.QuestionDetailsEntity;

public class QuestionViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<QuestionDetailsEntity> mQuestionDetailsEntities;

    public QuestionViewPagerAdapter(FragmentManager fm, List<QuestionDetailsEntity> questionDetailsEntities) {
        super(fm);
        this.mQuestionDetailsEntities = questionDetailsEntities;
    }

    @Override
    public Fragment getItem(int position) {
        return QuestionFragment.newInstance(mQuestionDetailsEntities.get(position));
    }

    @Override
    public int getCount() {
        return mQuestionDetailsEntities.size();
    }
}
