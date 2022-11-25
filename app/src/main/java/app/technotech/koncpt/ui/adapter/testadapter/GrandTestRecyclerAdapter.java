package app.technotech.koncpt.ui.adapter.testadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.application.GlideApp;
import app.technotech.koncpt.data.network.model.GrandTestModelResponse;
import app.technotech.koncpt.databinding.LayoutTestItemBinding;
import app.technotech.koncpt.utils.AppSharedPreference;


public class GrandTestRecyclerAdapter extends RecyclerView.Adapter<GrandTestRecyclerAdapter.ViewHolder> {

    final Context context;
    final List<GrandTestModelResponse.Datum> datumList;
    final OnItemClickListener listener;


    public GrandTestRecyclerAdapter(Context context, List<GrandTestModelResponse.Datum> datumList, OnItemClickListener listener) {
        this.context = context;
        this.datumList = datumList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {

            GrandTestModelResponse.Datum dataItem = datumList.get(position);
            holder.binding.txtTestDate.setText(getFormattedString(dataItem.getStartDate()));

            GlideApp.with(context)
                    .load(dataItem.getSubImage())
                    .placeholder(R.drawable.icon_test_examination)
                    .error(R.drawable.icon_test_examination)
                    .transform(new CenterCrop(), new RoundedCorners(context.getResources().getDimensionPixelSize(R.dimen._8sdp)))
                    .into(holder.binding.imageSubject);


            holder.binding.txtTestTitle.setText(dataItem.getTitle());
            String totalQuestions = dataItem.getTotalQuestions() + " Questions " + dataItem.getDueration() + " min";
            holder.binding.txtTestTotalQ.setText(totalQuestions);
            holder.binding.txtTestExpired.setText(getFormattedEndDateString(dataItem.getEndDate()));


            if (dataItem.getIsPaid().equals("1")) {

//                holder.binding.txtTestPro.setVisibility(View.VISIBLE);
                if (dataItem.getIsGiven().equals("1")) {
                    holder.binding.imageRight.setVisibility(View.VISIBLE);
                } else {

                }

            } else {
//                holder.binding.txtTestPro.setVisibility(View.VISIBLE);
                if (dataItem.getIsGiven().equals("1")) {
                    holder.binding.imageRight.setVisibility(View.VISIBLE);

                } else {

                }
            }


            if (new AppSharedPreference(context).getUserResponse().getPlan().equals("f")) {

                if (dataItem.getIsPaid().equals("1")) {
                    holder.binding.txtTestPro.setVisibility(View.VISIBLE);
                } else {
                    holder.binding.txtTestPro.setVisibility(View.GONE);
                }

            } else {
                holder.binding.txtTestPro.setVisibility(View.GONE);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(dataItem, position);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LayoutTestItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public interface OnItemClickListener {
        void onItemClick(GrandTestModelResponse.Datum datum, int position);
    }


    private String getFormattedString(String inputString) {

        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("MMMM yyyy");

        try {

            return myFormat.format(fromUser.parse(inputString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String getFormattedEndDateString(String inputString) {

        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy");

        try {

            return myFormat.format(fromUser.parse(inputString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
