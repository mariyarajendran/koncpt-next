package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.SuggestedCourseEntity;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.databinding.LayoutCourseItemBinding;
import app.technotech.koncpt.utils.DebugLog;
import es.dmoral.toasty.Toasty;

public class SuggestedCourseAdapter extends RecyclerView.Adapter<SuggestedCourseAdapter.SuggestedCourseViewHolder> {

    final List<HomeScreenModel.TestSeries> mCourseEntities;
    final Context context;
    final OnTestItemClicked listener;

    public SuggestedCourseAdapter(List<HomeScreenModel.TestSeries> mCourseEntities, Context context, OnTestItemClicked listener) {
        this.mCourseEntities = mCourseEntities;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestedCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_course_item, parent, false);
        return new SuggestedCourseAdapter.SuggestedCourseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SuggestedCourseViewHolder holder, int position) {
//        holder.onBind(mCourseEntities.get(position));

        HomeScreenModel.TestSeries data = mCourseEntities.get(position);

        holder.binding.txtCourseTitle.setText(data.getTitle());


        String datas  = data.getSubImage();
        DebugLog.e("Data -> " + datas);

        Glide.with(context)
                .load(data.getSubImage())
                .error(R.drawable.app_logo)
                .centerCrop()
                .into(holder.binding.imgCourse);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTestClick(position, data);
            }
        });

        holder.binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        if (mCourseEntities != null) {
            return mCourseEntities.size();
        }
        return 0;
    }

    public static class SuggestedCourseViewHolder extends RecyclerView.ViewHolder {

        private LayoutCourseItemBinding binding;

        public SuggestedCourseViewHolder(@NonNull View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            binding = DataBindingUtil.bind(itemView);
        }

//        void onBind(SuggestedCourseEntity suggestedCourseEntity) {
//            mTxtCourseTitle.setText(suggestedCourseEntity.getCourseTitle());
//        }
    }

    public void setCourseData(ArrayList<SuggestedCourseEntity> courseData) {
//        this.mCourseEntities = courseData;
//        notifyDataSetChanged();
    }


    public interface OnTestItemClicked {
        void onTestClick(int position, HomeScreenModel.TestSeries data);
    }
}
