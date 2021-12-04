package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.databinding.LayoutVideoItemBinding;
import app.technotech.koncpt.utils.AppSharedPreference;

public class SuggestedMaterClassAdapter extends RecyclerView.Adapter<SuggestedMaterClassAdapter.SuggestedVideoViewHolder> {

    final Context context;
    final List<HomeScreenModel.SuggestedVideo> mMasterClassEntities;
    final OnMasterSelect listener;


    public SuggestedMaterClassAdapter(Context context, List<HomeScreenModel.SuggestedVideo> mMasterClassEntities, OnMasterSelect listener) {
        this.context = context;
        this.mMasterClassEntities = mMasterClassEntities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestedVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_video_item, parent, false);
        return new SuggestedMaterClassAdapter.SuggestedVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedVideoViewHolder holder, int position) {

        HomeScreenModel.SuggestedVideo data = mMasterClassEntities.get(position);


        try {


            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    Glide.with(context)
                            .asBitmap()
                            .load(data.getClassVideo())
                            .into(holder.binding.thumbnail);

                }
            });


//            holder.binding.txtFacultyName.setText(data.getFacultyName());
            holder.binding.txtVideoTitle.setText(data.getSubjectTitle());

            String isFreeForUsers = data.getIsFreeForUsers();
            String isVideoForPlanB = data.getIsVideoForPlanB();
            String plan_type = "";


            if (new AppSharedPreference(context).getUserResponse().getPlan() != null) {
                plan_type = new AppSharedPreference(context).getUserResponse().getPlan();
            }


            if (plan_type.equals("f") || plan_type.equals("a") || plan_type.equals("A")) {

                if (isFreeForUsers.equals("1")) {
                    holder.binding.txtPro.setVisibility(View.GONE);
                } else {
                    holder.binding.txtPro.setVisibility(View.VISIBLE);
                }


            } else if (plan_type.equals("b") || plan_type.equals("B")) {

                if (isVideoForPlanB.equals("1")) {
                    holder.binding.txtPro.setVisibility(View.GONE);
                } else {

                    holder.binding.txtPro.setVisibility(View.VISIBLE);


                }

            } else if (plan_type.equals("c") || plan_type.equals("C") || plan_type.equals("d") || plan_type.equals("D")) {
                holder.binding.txtPro.setVisibility(View.GONE);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onMasterItemClick(position, data);
                }
            });


        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        if (mMasterClassEntities != null) {
            return mMasterClassEntities.size();
        }
        return 0;
    }

    public static class SuggestedVideoViewHolder extends RecyclerView.ViewHolder {


        private LayoutVideoItemBinding binding;

        public SuggestedVideoViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

        }

//        void onBind(SuggestedMasterClassEntity suggestedMasterClassEntity) {
//            mTxtMClassTitle.setText(suggestedMasterClassEntity.getMasterClassTitle());
//            mTxtMClassFacultyName.setText(suggestedMasterClassEntity.getMasterClassFaculty());
//        }
    }


    public interface OnMasterSelect {
        void onMasterItemClick(int position, HomeScreenModel.SuggestedVideo data);
    }
}
