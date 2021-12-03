package app.technotech.koncpt.ui.adapter;

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
import app.technotech.koncpt.data.network.model.QuestionBookmarkModel;
import app.technotech.koncpt.databinding.RowQuestionsBookmarkItemBinding;

public class QuestionsBookmarkRecyclerAdapter extends RecyclerView.Adapter<QuestionsBookmarkRecyclerAdapter.ViewHolder> {

    final Context context;
    final List<QuestionBookmarkModel.Datum> bookmarkList;
    final OnItemClickListener listener;

    public QuestionsBookmarkRecyclerAdapter(Context context, List<QuestionBookmarkModel.Datum> bookmarkList, OnItemClickListener listener) {
        this.context = context;
        this.bookmarkList = bookmarkList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_questions_bookmark_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        QuestionBookmarkModel.Datum data = bookmarkList.get(position);
        holder.binding.textBookmarkQuestion.setText(Html.fromHtml(data.getQuestion()));
        holder.binding.textSubjectQuestion.setText(data.getSubjectTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(data, position);
            }
        });

        holder.binding.buttonImageBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemDeleteBookmark(position);
            }
        });

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RowQuestionsBookmarkItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(QuestionBookmarkModel.Datum datum, int position);
        void onItemDeleteBookmark(int position);
    }


    public void removeItem(int position){
        bookmarkList.remove(position);
        notifyDataSetChanged();
    }

}
