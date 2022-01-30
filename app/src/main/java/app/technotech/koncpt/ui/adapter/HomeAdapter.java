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
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.data.network.model.VideoModel;
import app.technotech.koncpt.utils.TextUtil;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<HomeScreenModel.Data> dataList;

    public HomeAdapter(Context context, List<HomeScreenModel.Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvHomeDashboardTitle.setText(TextUtil.cutNull(dataList.get(position).getTitle()));
        holder.tvHomeDashboardCount.setText(TextUtil.cutNull(dataList.get(position).getTotal_count()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnVideoItemSelectedListener {
        void onVideoItemClick(VideoModel.ModuleDatum data, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHomeDashboardTitle, tvHomeDashboardCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHomeDashboardTitle = (TextView) itemView.findViewById(R.id.tvHomeDashboardTitle);
            tvHomeDashboardCount = (TextView) itemView.findViewById(R.id.tvHomeDashboardCount);
        }
    }
}
