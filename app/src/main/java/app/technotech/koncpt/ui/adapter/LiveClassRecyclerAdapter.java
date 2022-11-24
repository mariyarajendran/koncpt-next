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
import app.technotech.koncpt.data.network.model.LiveClassesModel;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.databinding.RowLiveClassesItemBinding;

public class LiveClassRecyclerAdapter extends RecyclerView.Adapter<LiveClassRecyclerAdapter.ViewHolder> {


    final Context context;
    final List<SubjectListDataModel.Datum> modelList;
    final OnItemClickListener listener;

    public LiveClassRecyclerAdapter(Context context, List<SubjectListDataModel.Datum> modelList, OnItemClickListener listener) {
        this.context = context;
        this.modelList = modelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_live_classes_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.textMasterClass.setText(modelList.get(position).getSubjectTitle());

        Glide.with(context)
                .load(modelList.get(position).getSubjectImage())
                .placeholder(R.drawable.img_question_bank)
                .error(R.drawable.img_question_bank)
                .into(holder.binding.imageMaster);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClick(position, modelList.get(position));
            }
        });

        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RowLiveClassesItemBinding binding;

      public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnItemClickListener{
        void OnItemClick(int position, SubjectListDataModel.Datum data);
    }

}
