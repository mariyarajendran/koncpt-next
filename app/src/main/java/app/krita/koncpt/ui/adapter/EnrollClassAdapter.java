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
import app.technotech.koncpt.databinding.RowEnrollItemBinding;

public class EnrollClassAdapter extends RecyclerView.Adapter<EnrollClassAdapter.ViewHolder> {


    final Context context;
    final List<LiveClassesListModel.EnrollDatum> datumList;
    final OnEnrollItemListener listener;

    public EnrollClassAdapter(Context context, List<LiveClassesListModel.EnrollDatum> datumList, OnEnrollItemListener listener) {
        this.context = context;
        this.datumList = datumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_enroll_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {

            LiveClassesListModel.EnrollDatum data = datumList.get(position);

            holder.binding.textViewLiveTitle.setText(data.getTitle());
            String dates = data.getDate() + " - " + data.getToDate();
            holder.binding.textViewLiveDate.setText(dates);

            holder.binding.buttonEnroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUnenroll(data, position);
                }
            });

            holder.binding.buttonViewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onViewDetail(data, position);
                }
            });

            holder.binding.buttonJoinNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onJoinNow(data, position);
                }
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RowEnrollItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnEnrollItemListener {

        void onUnenroll(LiveClassesListModel.EnrollDatum data, int position);

        void onViewDetail(LiveClassesListModel.EnrollDatum data, int position);

        void onJoinNow(LiveClassesListModel.EnrollDatum data, int position);
    }
}
