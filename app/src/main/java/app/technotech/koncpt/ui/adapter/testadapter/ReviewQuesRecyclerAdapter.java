package app.technotech.koncpt.ui.adapter.testadapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.AnswerKeyValue;
import app.technotech.koncpt.data.network.model.ReviewModelResponse;
import app.technotech.koncpt.databinding.RowReviewItemBinding;

public class ReviewQuesRecyclerAdapter extends RecyclerView.Adapter<ReviewQuesRecyclerAdapter.ViewHolder> {


    final Context context;
    final List<ReviewModelResponse.Datum> questionList;
    final List<AnswerKeyValue> answerList;
    final OnItemClickListener explainListner;


    public ReviewQuesRecyclerAdapter(Context context, List<ReviewModelResponse.Datum> questionList, List<AnswerKeyValue> answerList, OnItemClickListener explainListner) {
        this.context = context;
        this.questionList = questionList;
        this.answerList = answerList;
        this.explainListner = explainListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ReviewModelResponse.Datum dataItem = questionList.get(position);

        holder.binding.txtQuestion.setText(Html.fromHtml(dataItem.getQuestion()));
        holder.binding.txtOptionA.setText(dataItem.getAnswers().get(0).getOptionValue());
        holder.binding.txtOptionB.setText(dataItem.getAnswers().get(1).getOptionValue());
        holder.binding.txtOptionC.setText(dataItem.getAnswers().get(2).getOptionValue());
        holder.binding.txtOptionD.setText(dataItem.getAnswers().get(3).getOptionValue());

        switch (Integer.parseInt(dataItem.getCorrectAnswers())) {

            case 1:
                holder.binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                break;
            case 2:
                holder.binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                break;
            case 3:
                holder.binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                break;
            case 4:
                holder.binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                break;
        }


        for (int i = 0; i < answerList.size(); i++) {

            if (Integer.parseInt(answerList.get(i).getKey()) == dataItem.getId()) {

                if (Integer.parseInt(answerList.get(i).getValue()) != Integer.parseInt(dataItem.getCorrectAnswers())) {

                    switch (Integer.parseInt(answerList.get(i).getValue())) {

                        case 1:
                            holder.binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_false);
                            break;
                        case 2:
                            holder.binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_false);
                            break;
                        case 3:
                            holder.binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_false);
                            break;
                        case 4:
                            holder.binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_false);
                            break;
                    }

                }
            }
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explainListner.onExplanationItem(position);
            }
        });

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RowReviewItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnItemClickListener {
        void onExplanationItem(int position);
    }
}
