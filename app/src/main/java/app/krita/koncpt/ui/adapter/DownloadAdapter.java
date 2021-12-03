package app.technotech.koncpt.ui.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.DBHelper;
import app.technotech.koncpt.data.network.model.DownloadModel;
import app.technotech.koncpt.databinding.DownloadsItemBinding;
//import app.technotech.koncpt.ui.activity.PlayVideo;
import app.technotech.koncpt.utils.GeneralUtils;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {

    Context context;
    ArrayList<DownloadModel> downloadModelArrayList;
    final OnVideoItemSelectedListener listener;
    Bitmap thumbs = null;
    DBHelper dbHelper;
    ProgressDialog progressDialog;

    public DownloadAdapter(Context context, ArrayList<DownloadModel> downloadModelArrayList, OnVideoItemSelectedListener listener) {
        this.context = context;
        this.downloadModelArrayList = downloadModelArrayList;
        this.listener = listener;
        progressDialog = new ProgressDialog(context);
        dbHelper = new DBHelper(context);

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.downloads_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.txtTitleDownloads.setText(downloadModelArrayList.get(position).getSubject_title());
        holder.binding.txtDescriptionDownloads.setText(downloadModelArrayList.get(position).getDescription());

        float radius = context.getResources().getDimension(R.dimen._16sdp);

        holder.binding.imgThumbnail.setShapeAppearanceModel(holder.binding.imgThumbnail
                .getShapeAppearanceModel()
                .toBuilder()
                .setAllCornerSizes(radius)
                .build());

        try {
            thumbs = GeneralUtils.retriveVideoFrameFromVideo(downloadModelArrayList.get(position).getVideo_url());
            Glide.with(context)
                    .asBitmap()
                    .load(thumbs)
                    .into(holder.binding.imgThumbnail);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.d("videoName", downloadModelArrayList.get(position).getSubject_title() + "");
        Log.d("videoDescription", downloadModelArrayList.get(position).getDescription() + "");
        Log.d("videoUrl", downloadModelArrayList.get(position).getVideo_url() + "");
        Log.d("videoId", downloadModelArrayList.get(position).getVideo_id() + "");


        holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVideoDelete(downloadModelArrayList.get(position).getVideo_id(), downloadModelArrayList.get(position).getVideo_url(), position);
            }
        });

        holder.binding.imgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(context, PlayVideo.class);
//                intent.putExtra("video_url",downloadModelArrayList.get(position).getVideo_url());
//                context.startActivity(intent);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onVideoItemClick(downloadModelArrayList.get(0), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (downloadModelArrayList != null) {
            return downloadModelArrayList.size();

        } else
            return 0;
    }


    public void removeItem(int position) {


        downloadModelArrayList.remove(position);
        notifyDataSetChanged();
    }


    public interface OnVideoItemSelectedListener {
        void onVideoItemClick(DownloadModel data, int position);

        void onVideoDelete(String id, String video_path, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        DownloadsItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }
    }
}
