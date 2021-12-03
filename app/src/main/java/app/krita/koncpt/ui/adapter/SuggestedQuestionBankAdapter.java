package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.SuggestedQBankEntity;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.databinding.LayoutQuestionBankItemBinding;


public class SuggestedQuestionBankAdapter extends RecyclerView.Adapter<SuggestedQuestionBankAdapter.SuggestedQuestionBankViewHolder> {

    final Context context;
    final List<HomeScreenModel.PopularMcq> mSuggestedQBankEntities;
    final OnMcqItemSelected listener;

    public SuggestedQuestionBankAdapter(Context context, List<HomeScreenModel.PopularMcq> mSuggestedQBankEntities, OnMcqItemSelected listener) {
        this.context = context;
        this.mSuggestedQBankEntities = mSuggestedQBankEntities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestedQuestionBankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_bank_item, parent, false);
        return new SuggestedQuestionBankAdapter.SuggestedQuestionBankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedQuestionBankViewHolder holder, int position) {

        HomeScreenModel.PopularMcq data = mSuggestedQBankEntities.get(position);
        holder.binding.txtSubject.setText(data.getSubjectTitle());
        String mcq = Integer.toString(data.getTotalMcq()) + " MCQ";
        holder.binding.txtNoOfMcq.setText(mcq);


        Glide.with(context)
                .load(data.getSubjectImage())
                .error(R.drawable.mcq_q_bank)
                .placeholder(R.drawable.mcq_q_bank)
                .into(holder.binding.imgMcqThumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMcqItemClick(position, data);
            }
        });



    }

    @Override
    public int getItemCount() {
        if(mSuggestedQBankEntities!=null){
            return mSuggestedQBankEntities.size();
        }
        return 0;
    }

    public static class SuggestedQuestionBankViewHolder extends RecyclerView.ViewHolder{

     private LayoutQuestionBankItemBinding binding;


        public SuggestedQuestionBankViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

//        void onBind(SuggestedQBankEntity suggestedQBankEntity) {
//            mTxtSubject.setText(suggestedQBankEntity.getQBankTitle());
//            mTxtNoOfMcq.setText(suggestedQBankEntity.getQBankCount());
//        }
    }

    public void setQuestionBankData(ArrayList<SuggestedQBankEntity> questionBankData){
//        this.mSuggestedQBankEntities=questionBankData;
//        notifyDataSetChanged();
    }


    public interface OnMcqItemSelected{
        void onMcqItemClick(int position, HomeScreenModel.PopularMcq data);
    }

}
