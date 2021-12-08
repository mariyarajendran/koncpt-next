package app.technotech.koncpt.ui.adapter.viewpageradapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import app.technotech.koncpt.QuestionFragment;
import app.technotech.koncpt.data.local.entites.QuestionDetailsEntity;

public class QuestionViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<QuestionDetailsEntity> mQuestionDetailsEntities;
    private AnswerListener answerListener;

    public QuestionViewPagerAdapter(FragmentManager fm, List<QuestionDetailsEntity> questionDetailsEntities, AnswerListener answerListener) {
        super(fm);
        this.mQuestionDetailsEntities = questionDetailsEntities;
        this.answerListener = answerListener;
    }

    @Override
    public Fragment getItem(int position) {
        answerListener.onAnswerClicked(position);
        return QuestionFragment.newInstance(mQuestionDetailsEntities.get(position));
    }

    @Override
    public int getCount() {
        return mQuestionDetailsEntities.size();
    }

    public interface AnswerListener {
        void onAnswerClicked(int position);
    }
}

