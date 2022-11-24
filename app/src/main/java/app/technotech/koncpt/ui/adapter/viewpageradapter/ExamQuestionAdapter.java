package app.technotech.koncpt.ui.adapter.viewpageradapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.data.network.model.ExamQuestionsModelResponse;
import app.technotech.koncpt.ui.fragments.testTabs.ExamQuestionFragment;

public class ExamQuestionAdapter extends FragmentStatePagerAdapter {


    List<ExamQuestionsModelResponse.Question> questionList = new ArrayList<>();

    public ExamQuestionAdapter(@NonNull FragmentManager fm, int behavior, List<ExamQuestionsModelResponse.Question> questionList) {
        super(fm, behavior);
        this.questionList =questionList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ExamQuestionFragment.newInstance(questionList.get(position));
    }

    @Override
    public int getCount() {
        return questionList.size();
    }
}
