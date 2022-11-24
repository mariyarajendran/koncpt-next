package app.technotech.koncpt.data.network.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class QuestionBankGroup extends ExpandableGroup<QuestionBank> {

    public int order = -1;

    public QuestionBankGroup(String title, List<QuestionBank> items) {
        super(title, items);
    }
}
