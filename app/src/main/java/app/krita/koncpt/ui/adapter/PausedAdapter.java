package app.technotech.koncpt.ui.adapter;

import android.content.Context;
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
import app.technotech.koncpt.databinding.FragmentItemAllBinding;
import app.technotech.koncpt.utils.AppSharedPreference;

public class PausedAdapter extends RecyclerView.Adapter<PausedAdapter.ViewHolder> {

    final Context context;
    final List<VideoModel.ModuleDatum> moduleDatumList;
    final OnVideoItemSelectedListener listener;
    String plan;

    public PausedAdapter(Context context, List<VideoModel.ModuleDatum> moduleDatumList, OnVideoItemSelectedListener listener) {
        this.context = context;
        this.moduleDatumList = moduleDatumList;
        this.listener = listener;

        //TODO DIPLAICODE REMOVED
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_all, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PausedAdapter.ViewHolder holder, int position) {
        VideoModel.ModuleDatum data = moduleDatumList.get(position);


        holder.binding.txtTopicName.setText(data.getClassTitle());
        holder.binding.txtDescription.setText(data.getPaushedTime());
//        holder.binding.txtTime.setText(data.);
        holder.binding.txtCount.setText((position + 1) + "");

        Glide.with(context)
                .load(data.getFacultyImage())
                .placeholder(R.drawable.dummy_img)
                .error(R.drawable.dummy_img)
                .into(holder.binding.imgTopicImage);

        holder.binding.txtRating.setText(data.getVideoRate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVideoItemClick(data, position);
            }
        });

        plan = new AppSharedPreference(context).getUserResponse().getPlan();

        if (data.getType().equals("")) {
            holder.binding.lnrType.setVisibility(View.INVISIBLE);
        } else if (data.getType().matches("1")) {
            holder.binding.lnrType.setVisibility(View.VISIBLE);

        } else if (data.getType().matches("0")) {
            holder.binding.lnrType.setVisibility(View.INVISIBLE);
        }

        if (data.getIsFreeForUsers().equals("1")) {
            holder.binding.lnrPro.setVisibility(View.INVISIBLE);

        } else if (data.getIsFreeForUsers().equals("0")) {
            holder.binding.lnrPro.setVisibility(View.VISIBLE);
        } else if (plan.matches("f") || plan.matches("F")) {
            if (data.getIsFreeForUsers().equals("1")) {
                holder.binding.lnrPro.setVisibility(View.INVISIBLE);
            } else if (data.getIsFreeForUsers().equals("0")) {
                holder.binding.lnrPro.setVisibility(View.VISIBLE);
            }
        } else if (plan.matches("b") || plan.matches("B")) {
            Log.d("planB", "planB");
            if (data.getIsVideoForPlanB().equals("1")) {
                holder.binding.lnrPro.setVisibility(View.INVISIBLE);
            } else if (data.getIsVideoForPlanB().equals("0")) {
                holder.binding.lnrPro.setVisibility(View.VISIBLE);
            }

        } else if (plan.matches("c") || plan.matches("C")) {
            holder.binding.lnrPro.setVisibility(View.INVISIBLE);
        } else if (plan.matches("d") || plan.matches("D")) {
            holder.binding.lnrPro.setVisibility(View.INVISIBLE);


        }


    }

    @Override
    public int getItemCount() {
        return moduleDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FragmentItemAllBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }

        void onBind(VideoModel.ModuleDatum moduleName) {
//
//            txtTopicName.setText(moduleName.getTopicName());
//            txtDescription.setText(moduleName.getDescription());


        }
    }


    public interface OnVideoItemSelectedListener {
        void onVideoItemClick(VideoModel.ModuleDatum data, int position);
    }
}

