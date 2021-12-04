package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SearchModel;
import app.technotech.koncpt.databinding.RowSearchItemBinding;
import app.technotech.koncpt.ui.viewmodels.SearchViewModel;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {


    final Context context;
    final List<SearchModel.Subject>  data;
    final OnSearchItemClick listener;


    public SearchRecyclerAdapter(Context context, List<SearchModel.Subject> data, OnSearchItemClick listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SearchModel.Subject item = data.get(position);

        holder.binding.txtSubjectTitle.setText(item.getSubjectTitle());
        holder.binding.executePendingBindings();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClickListener(item, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RowSearchItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnSearchItemClick{

        void onItemClickListener(SearchModel.Subject  data, int position);

    }

}
