package app.technotech.koncpt.ui.adapter.custommoduleadapters;

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
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.RowReviewItemBinding;

public class CustomReviewAdapter extends RecyclerView.Adapter<CustomReviewAdapter.ViewHolder> {


    final Context context;
    final List<CustomExamModel.Datum> questionList;
    final OnItemClickListener listener;
    private String correctAnswer;

    public CustomReviewAdapter(Context context, List<CustomExamModel.Datum > questionList, OnItemClickListener listener) {
        this.context = context;
        this.questionList = questionList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_review_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{

            CustomExamModel.Datum data = questionList.get(position);

            holder.binding.txtQuestion.setText(Html.fromHtml(data.getQuestion()));
            holder.binding.txtOptionA.setText(data.getAnswers().get(0).getOptionValue());
            holder.binding.txtOptionB.setText(data.getAnswers().get(1).getOptionValue());
            holder.binding.txtOptionC.setText(data.getAnswers().get(2).getOptionValue());
            holder.binding.txtOptionD.setText(data.getAnswers().get(3).getOptionValue());



            if (data.getCorrectAnswers().equals("1")) {

                correctAnswer = data.getCorrectAnswers();

            } else if (data.getCorrectAnswers().equals("2")) {

                correctAnswer = data.getCorrectAnswers();

            } else if (data.getCorrectAnswers().equals("3")) {

                correctAnswer = data.getCorrectAnswers();

            } else if (data.getCorrectAnswers().equals("4")) {

                correctAnswer = data.getCorrectAnswers();
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onExplanationItem(position);
                }
            });


            itemsSelection(holder, data);


        } catch (Exception ex){
            ex.printStackTrace();
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

    private void itemsSelection(ViewHolder holder, CustomExamModel.Datum data) {


            holder.binding.txtOptionA.setClickable(false);
            holder.binding.txtOptionA.setEnabled(false);
            holder.binding.txtOptionB.setClickable(false);
            holder.binding.txtOptionB.setEnabled(false);
            holder.binding.txtOptionC.setClickable(false);
            holder.binding.txtOptionC.setEnabled(false);
            holder.binding.txtOptionD.setClickable(false);
            holder.binding.txtOptionD.setEnabled(false);


            if (data.getCorrectAnswers().equals(data.getSelectedAnswer())) {


                if (data.getSelectedAnswer().equals("1")) {
                    holder.binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (data.getSelectedAnswer().equals("2")) {
                    holder.binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (data.getSelectedAnswer().equals("3")) {
                    holder.binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (data.getSelectedAnswer().equals("4")) {
                    holder.binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }


            } else {

                if (data.getSelectedAnswer().equals("1")) {
                    holder.binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (data.getSelectedAnswer().equals("2")) {
                    holder.binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (data.getSelectedAnswer().equals("3")) {
                    holder.binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (data.getSelectedAnswer().equals("4")) {
                    holder.binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }


                if (data.getSelectedAnswer().equals("1")) {
                    holder.binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_false);
                } else if (data.getSelectedAnswer().equals("2")) {
                    holder.binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_false);
                } else if (data.getSelectedAnswer().equals("3")) {
                    holder.binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_false);
                } else if (data.getSelectedAnswer().equals("4")) {
                    holder.binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_false);
                }



        }

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


    private void selectedAnswerResult() {


    }

}
