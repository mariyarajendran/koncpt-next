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
import app.technotech.koncpt.data.network.model.SubjectBookmarkModel;
import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.databinding.RowBookmarksItemBinding;

public class BookmarkRecyclerAdapter extends RecyclerView.Adapter<BookmarkRecyclerAdapter.ViewHolder> {

    final Context context;
    final List<SubjectBookmarkModel.Datum> bookmarkList;
    final OnItemClickListener listener;

    public BookmarkRecyclerAdapter(Context context, List<SubjectBookmarkModel.Datum> bookmarkList, OnItemClickListener listener) {
        this.context = context;
        this.bookmarkList = bookmarkList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_bookmarks_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SubjectBookmarkModel.Datum data = bookmarkList.get(position);
        String itemName = data.getSubjectTitle() + " (" + data.getTotalMcq()+ ")";
        holder.binding.textBookmarkName.setText(itemName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(data, position);
            }
        });

        holder.binding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RowBookmarksItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(SubjectBookmarkModel.Datum datum, int position);
    }
}
