package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.application.GlideApp;
import app.technotech.koncpt.data.network.model.DailyHuntModel;
import app.technotech.koncpt.databinding.LayoutHuntItemBinding;
import app.technotech.koncpt.utils.GeneralUtils;

public class DailyHuntAdapter extends RecyclerView.Adapter<DailyHuntAdapter.ViewHolder> {

    final Context context;
    final List<DailyHuntModel.Datum> modelList;
    final OnItemClickListener listener;

    public DailyHuntAdapter(Context context, List<DailyHuntModel.Datum> modelList, OnItemClickListener listener) {
        this.context = context;
        this.modelList = modelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_hunt_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            int radius = context.getResources().getDimensionPixelOffset(R.dimen._4sdp);
            final DailyHuntModel.Datum data = modelList.get(position);
            GlideApp.with(context)
                    .load(data.getImage())
                    .apply(new RequestOptions()
                            .transform(new CenterCrop(), new RoundedCorners(radius))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.zm_image_placeholder)
                            .error(R.drawable.zm_image_placeholder)
                    ).into(holder.binding.imageHuntProfile);

            holder.binding.imageHuntProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GeneralUtils.openImageDialog(context, data.getImage());
                }
            });
            holder.binding.textHuntDescription
                    .setAnimationDuration(500)
                    .setEllipsizedText("Read More")
                    .setVisibleLines(3)
                    .setIsExpanded(false)
                    .setEllipsizedTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            holder.binding.textHuntTitle.setText(data.getTitle());
            holder.binding.textHuntDescription.setText(data.getContent());
            holder.binding.textHuntDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.binding.textHuntDescription.toggle();
                }
            });
            holder.binding.textHuntCreated.setText(data.getCreatedAt());
            holder.binding.executePendingBindings();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LayoutHuntItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(modelList.get(getAdapterPosition()), getAdapterPosition());
        }
    }


    public interface OnItemClickListener {
        void onItemClick(DailyHuntModel.Datum datum, int position);
    }
}
