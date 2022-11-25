package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.NotesModel;
import es.dmoral.toasty.Toasty;


public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    Context context;
    List<NotesModel.VideoCutTimeTopic> moduleDatumList;
    final OnVideoItemSelectedListener listener;

    public ClassAdapter(Context context, List<NotesModel.VideoCutTimeTopic> moduleDatumList,OnVideoItemSelectedListener listener) {
        this.context = context;
        this.moduleDatumList = moduleDatumList;
       this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_video_item, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesModel.VideoCutTimeTopic data = moduleDatumList.get(position);
        holder.txtTime.setText(data.getVideoTime());
        holder.txtTitle.setText(data.getVideoCutTopic());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onVideoItemClick(data, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (moduleDatumList != null) {
            return moduleDatumList.size();
        }
        return 0;
    }

    public interface OnVideoItemSelectedListener {
        void onVideoItemClick(NotesModel.VideoCutTimeTopic data, int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime,txtTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTime=(TextView)itemView.findViewById(R.id.txtTime);
            txtTitle=(TextView)itemView.findViewById(R.id.txtTitle);

        }
    }
}
