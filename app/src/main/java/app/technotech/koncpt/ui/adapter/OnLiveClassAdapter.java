package app.technotech.koncpt.ui.adapter;

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
import app.technotech.koncpt.databinding.RowUpcomingItemBinding;
import app.technotech.koncpt.utils.GeneralUtils;

public class OnLiveClassAdapter extends RecyclerView.Adapter<OnLiveClassAdapter.ViewHolder> {
    final Context context;
    final List<LiveClassesListModel.LiveClassDatum> datumList;
    final OnLiveClassItemClick listener;

    public OnLiveClassAdapter(Context context, List<LiveClassesListModel.LiveClassDatum> datumList, OnLiveClassItemClick listener) {
        this.context = context;
        this.datumList = datumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_upcoming_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {

            LiveClassesListModel.LiveClassDatum data = datumList.get(position);
            holder.binding.textViewMonth.setText(GeneralUtils.getMonthFromDate(data.getToDate()));
            holder.binding.textViewDate.setText(GeneralUtils.getDayFromDate(data.getToDate()));
            holder.binding.textViewLiveTitle.setText(data.getTitle());

            String dates = data.getDate() + " - " + data.getToDate();
            holder.binding.textViewLiveDate.setText(dates);
            holder.binding.textViewLiveName.setText(data.getName());

            holder.binding.textViewLiveDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onViewDetailItemClick(data, position);
                }
            });

            holder.binding.buttonEnroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEnrollItemClick(data, position);
                }
            });

            holder.binding.executePendingBindings();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RowUpcomingItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }
    }


    public interface OnLiveClassItemClick {
        void onViewDetailItemClick(LiveClassesListModel.LiveClassDatum data, int position);

        void onEnrollItemClick(LiveClassesListModel.LiveClassDatum data, int position);
    }

}


