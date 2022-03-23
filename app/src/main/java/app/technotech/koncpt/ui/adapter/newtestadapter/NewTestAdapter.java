package app.technotech.koncpt.ui.adapter.newtestadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.QuestionBank;
import app.technotech.koncpt.data.network.model.QuestionBankGroup;
import app.technotech.koncpt.ui.adapter.viewholder.NewTestChildViewHolder;
import app.technotech.koncpt.ui.adapter.viewholder.NewTestGroupViewHolder;
import app.technotech.koncpt.ui.adapter.viewholder.QuestionBankChildViewHolder;
import app.technotech.koncpt.ui.adapter.viewholder.QuestionBankGroupViewHolder;

public class NewTestAdapter extends ExpandableRecyclerViewAdapter<NewTestGroupViewHolder, NewTestChildViewHolder> {
    final List<? extends ExpandableGroup> groups;
    final AppCompatActivity context;
    final String levelId;

    public NewTestAdapter(AppCompatActivity context, List<? extends ExpandableGroup> groups, String levelId) {
        super(groups);
        this.context = context;
        this.groups = groups;
        this.levelId = levelId;
    }

    @Override
    public NewTestGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_question_bank_list_item_group, parent, false);
        return new NewTestGroupViewHolder(view);
    }

    @Override
    public NewTestChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_question_bank_list_item, parent, false);
        return new NewTestChildViewHolder(view, levelId);
    }

    @Override
    public void onBindChildViewHolder(NewTestChildViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final QuestionBank questionBank = ((QuestionBankGroup) group).getItems().get(childIndex);
        holder.setSubject(holder, context, questionBank, childIndex);
    }

    @Override
    public void onBindGroupViewHolder(NewTestGroupViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.binding.txtYear.setText(group.getTitle());
    }

    public void expandAllGroups() {
        for (int i = 0; i < groups.size(); i++) {
            if (!isGroupExpanded(groups.get(i))) {
                onGroupClick(expandableList.getFlattenedGroupIndex(i));
            }
        }
    }
}
