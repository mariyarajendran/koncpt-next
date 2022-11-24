package app.technotech.koncpt.ui.adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.databinding.FragmentItemCompletedBinding;
import app.technotech.koncpt.utils.AppSharedPreference;


public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder> {
    Context context;
    List<VideoModel.ModuleDatum> moduleDatumList;
    final CompletedAdapter.OnVideoItemSelectedListener listener;
    String plan;

    public CompletedAdapter(Context context, List<VideoModel.ModuleDatum> moduleDatumList, OnVideoItemSelectedListener listener) {
        this.context = context;
        this.moduleDatumList = moduleDatumList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CompletedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_completed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedAdapter.ViewHolder holder, int position) {

        plan = new AppSharedPreference(context).getUserResponse().getPlan();
        VideoModel.ModuleDatum data = moduleDatumList.get(position);


        holder.binding.txtTopicName.setText(data.getClassTitle());
        holder.binding.txtDescription.setText(data.getPaushedTime());
        holder.binding.txtCount.setText((position + 1) + "");

        holder.binding.txtRating.setText(data.getVideoRate());

        Glide.with(context)
                .load(data.getFacultyImage())
                .placeholder(R.drawable.dummy_img)
                .error(R.drawable.dummy_img)
                .into(holder.binding.imgTopicImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVideoItemClick(data, position);
            }
        });
        holder.binding.lnrType.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return moduleDatumList.size();
    }

    public interface OnVideoItemSelectedListener {
        void onVideoItemClick(VideoModel.ModuleDatum data, int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //FragmentItemCompletedBinding is xml name binding
        private FragmentItemCompletedBinding binding;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }
    }
}
