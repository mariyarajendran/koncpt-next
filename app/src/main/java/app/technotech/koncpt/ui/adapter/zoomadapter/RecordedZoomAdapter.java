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
import app.technotech.koncpt.data.network.model.RecordedDataModel;
import app.technotech.koncpt.databinding.RecordedZoomItemBinding;
import app.technotech.koncpt.ui.callbacks.RecordedZoomCallbacks;

public class RecordedZoomAdapter extends RecyclerView.Adapter<RecordedZoomAdapter.ViewHolder> {
    final Context context;
    final List<RecordedDataModel.RecordedData> recordedDataList;
    RecordedZoomCallbacks recordedZoomCallbacks;

    public RecordedZoomAdapter(Context context, List<RecordedDataModel.RecordedData> recordedDataList, RecordedZoomCallbacks recordedZoomCallbacks) {
        this.context = context;
        this.recordedDataList = recordedDataList;
        this.recordedZoomCallbacks = recordedZoomCallbacks;
    }

    @NonNull
    @Override
    public RecordedZoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recorded_zoom_item, parent, false);
        return new RecordedZoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordedZoomAdapter.ViewHolder holder, int position) {
        RecordedDataModel.RecordedData data = recordedDataList.get(position);
        holder.binding.tvSubjectName.setText(data.getTitle());
        holder.binding.tvFacultyName.setText(data.getFacultyName());
        holder.binding.tvMeetingDateTime.setText(String.format("%s %s-%s", data.getToDate(), data.getFromTime(), data.getToTime()));
        holder.binding.crdRzi.setOnClickListener(view -> recordedZoomCallbacks.onTap(position));
    }

    @Override
    public int getItemCount() {
        return recordedDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecordedZoomItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

