package app.technotech.koncpt.ui.adapter.zoomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.LiveClassesListModel;
import app.technotech.koncpt.databinding.CompletedZoomItemBinding;
import app.technotech.koncpt.ui.callbacks.CompletedZoomCallbacks;

public class CompletedZoomAdapter extends RecyclerView.Adapter<CompletedZoomAdapter.ViewHolder> {
    final Context context;
    final List<LiveClassesListModel.LiveClassDatum> classDatumList;
    CompletedZoomCallbacks callbacks;

    public CompletedZoomAdapter(Context context, List<LiveClassesListModel.LiveClassDatum> classDatumList, CompletedZoomCallbacks callbacks) {
        this.context = context;
        this.classDatumList = classDatumList;
        this.callbacks = callbacks;
    }

    @NonNull
    @Override
    public CompletedZoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_zoom_item, parent, false);
        return new CompletedZoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedZoomAdapter.ViewHolder holder, int position) {
        LiveClassesListModel.LiveClassDatum data = classDatumList.get(position);
        holder.binding.tvSubjectName.setText(data.getTitle());
        holder.binding.crdCzi.setOnClickListener(view -> callbacks.onTap(position));
    }

    @Override
    public int getItemCount() {
        return classDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CompletedZoomItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

