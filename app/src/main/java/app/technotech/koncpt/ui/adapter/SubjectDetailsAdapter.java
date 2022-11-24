package app.technotech.koncpt.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.SubjectEntity;
import app.technotech.koncpt.databinding.LayoutSubjectDetailsItemBinding;


public class SubjectDetailsAdapter extends RecyclerView.Adapter<SubjectDetailsAdapter.SubjectDetailsViewHolder> {

    private ArrayList<SubjectEntity> mSubjectEntities;

    public SubjectDetailsAdapter() {

    }

    @NonNull
    @Override
    public SubjectDetailsAdapter.SubjectDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subject_details_item, parent, false);
        return new SubjectDetailsAdapter.SubjectDetailsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SubjectDetailsAdapter.SubjectDetailsViewHolder holder, int position) {
        holder.onBind(holder, mSubjectEntities.get(position));
    }

    @Override
    public int getItemCount() {
        if (mSubjectEntities != null) {
            return mSubjectEntities.size();
        }
        return 0;
    }

    public static class SubjectDetailsViewHolder extends RecyclerView.ViewHolder {


        private LayoutSubjectDetailsItemBinding binding;

        public SubjectDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @SuppressLint("SetTextI18n")
        void onBind(SubjectDetailsViewHolder holder, SubjectEntity subjectEntity) {
            holder.binding.txtChapterName.setText(subjectEntity.getChapterTitle());
            holder.binding.txtChapterCount.setText("" + (getAdapterPosition() + 1));
        }
    }

    public void setSubjectData(ArrayList<SubjectEntity> subjectData) {
        this.mSubjectEntities = subjectData;
        notifyDataSetChanged();
    }
}
