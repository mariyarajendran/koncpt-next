package app.technotech.koncpt.ui.adapter.testadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.data.network.model.SubjectQuestionBankGroup;
import app.technotech.koncpt.databinding.LayoutSubjectDetailsItemBinding;
import app.technotech.koncpt.databinding.RowSectionHeaderBinding;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.TextUtil;

public class AllTestRecyclerAdapter extends ExpandableRecyclerViewAdapter<AllTestRecyclerAdapter.ParentViewHolder, AllTestRecyclerAdapter.ChildrenViewholder> {

    final List<? extends ExpandableGroup> groups;
    final AppCompatActivity context;
    final OnItemClickListener listener;

    public AllTestRecyclerAdapter(AppCompatActivity context, List<? extends ExpandableGroup> groups, OnItemClickListener listener) {
        super(groups);
        this.context = context;
        this.groups = groups;
        this.listener = listener;
    }

    @Override
    public ParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return new ParentViewHolder(LayoutInflater.from(context).inflate(R.layout.row_section_header, parent, false));
    }

    @Override
    public ChildrenViewholder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return new ChildrenViewholder(LayoutInflater.from(context).inflate(R.layout.layout_subject_details_item, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildrenViewholder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final SubjectModel.ModuleDatum data = ((SubjectQuestionBankGroup) group).getItems().get(childIndex);
        holder.binding.txtChapterName.setText(data.getTopicName());
        String mcqs = data.getTotalMcq() + "MCQs";
        holder.binding.txtNoOfMcq.setText(mcqs);
        holder.binding.txtChapterCount.setText((childIndex + 1) + "");
        holder.binding.txtTestTotalQ.setText(TextUtil.cutNull(data.getTopicRates()));
        Glide.with(context)
                .load(R.drawable.app_logo)
                .placeholder(R.drawable.dummy_img)
                .error(R.drawable.ic_image_placeholder)
                .into(holder.binding.imgSubject);
        String plantType = new AppSharedPreference(context).getUserResponse().getPlan();
        if (plantType.equals("f")) {
            if (data.getIsPaid() == 0) {
                //free
                holder.binding.txtPro.setVisibility(View.GONE);
            } else if (data.getIsPaid() == 1) {
                //paid
                holder.binding.txtPro.setVisibility(View.GONE);

            }
        } else {
            holder.binding.txtPro.setVisibility(View.GONE);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(data, childIndex);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(ParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        //holder.binding.textHeaderItem.setText(group.getTitle());
    }


    public class ParentViewHolder extends GroupViewHolder {
        private RowSectionHeaderBinding binding;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public class ChildrenViewholder extends ChildViewHolder {
        private LayoutSubjectDetailsItemBinding binding;

        public ChildrenViewholder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public void expandAllGroups() {
        for (int i = 0; i < groups.size(); i++) {
            if (!isGroupExpanded(groups.get(i))) {
                onGroupClick(expandableList.getFlattenedGroupIndex(i));
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(SubjectModel.ModuleDatum data, int position);
    }

}
