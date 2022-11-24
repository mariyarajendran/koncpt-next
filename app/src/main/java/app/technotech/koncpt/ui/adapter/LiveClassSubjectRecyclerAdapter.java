package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.databinding.RowLiveClassesItemBinding;
import app.technotech.koncpt.databinding.RowLiveClassesSubjectItemBinding;

public class LiveClassSubjectRecyclerAdapter extends RecyclerView.Adapter<LiveClassSubjectRecyclerAdapter.ViewHolder> {


    final Context context;
    final List<SubjectListDataModel.Datum> modelList;
    final OnItemClickListener listener;

    public LiveClassSubjectRecyclerAdapter(Context context, List<SubjectListDataModel.Datum> modelList, OnItemClickListener listener) {
        this.context = context;
        this.modelList = modelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_live_classes_subject_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtSubjectName.setText(modelList.get(position).getSubjectTitle());
        holder.itemView.setOnClickListener(view -> listener.OnItemClick(position, modelList.get(position)));
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RowLiveClassesSubjectItemBinding binding;

      public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnItemClickListener{
        void OnItemClick(int position, SubjectListDataModel.Datum data);
    }

}
