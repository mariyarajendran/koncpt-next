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
import app.technotech.koncpt.databinding.CompletedZoomItemBinding;
import app.technotech.koncpt.ui.callbacks.RecordedZoomCallbacks;

public class CompletedZoomAdapter extends RecyclerView.Adapter<CompletedZoomAdapter.ViewHolder> {
    final Context context;
    final List<RecordedDataModel.RecordedData> recordedDataList;
    RecordedZoomCallbacks recordedZoomCallbacks;

    public CompletedZoomAdapter(Context context, List<RecordedDataModel.RecordedData> recordedDataList, RecordedZoomCallbacks recordedZoomCallbacks) {
        this.context = context;
        this.recordedDataList = recordedDataList;
        this.recordedZoomCallbacks = recordedZoomCallbacks;
    }

    @NonNull
    @Override
    public CompletedZoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qbank_level_item, parent, false);
        return new CompletedZoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedZoomAdapter.ViewHolder holder, int position) {
        RecordedDataModel.RecordedData data = recordedDataList.get(position);
        holder.binding.tvSubjectName.setText(data.getId());
        holder.binding.crdCzi.setOnClickListener(view -> recordedZoomCallbacks.onTap(position));
    }

    @Override
    public int getItemCount() {
        return recordedDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CompletedZoomItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

