package app.technotech.koncpt.ui.adapter.custommoduleadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SubjectListModel;
import app.technotech.koncpt.databinding.LayoutSubjectItemBinding;


public class CustomModuleSubjectAdapter extends RecyclerView.Adapter<CustomModuleSubjectAdapter.customModuleSubjectViewHolder> {

    final ArrayList<SubjectListModel.Datum> mSubjectArrayList;
    final Context context;

    public CustomModuleSubjectAdapter(Context context, ArrayList<SubjectListModel.Datum> subjectArrayList) {
        this.context = context;
        this.mSubjectArrayList = subjectArrayList;
    }

    @NonNull
    @Override
    public CustomModuleSubjectAdapter.customModuleSubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subject_item, parent, false);
        return new CustomModuleSubjectAdapter.customModuleSubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomModuleSubjectAdapter.customModuleSubjectViewHolder holder, int position) {
        SubjectListModel.Datum data = mSubjectArrayList.get(position);
        holder.binding.radioBtnSubject.setText(data.getSubjectTitle());
        holder.binding.radioBtnSubject.setChecked(data.isSelected());

        holder.binding.radioBtnSubject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.binding.radioBtnSubject.setChecked(b);
                data.setSelected(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mSubjectArrayList != null) {
            return mSubjectArrayList.size();
        }
        return 0;
    }

    public static class customModuleSubjectViewHolder extends RecyclerView.ViewHolder {

        private LayoutSubjectItemBinding binding;

        public customModuleSubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }
}
