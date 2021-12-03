package app.technotech.koncpt.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.ReferenceBookEntity;
import app.technotech.koncpt.databinding.LayoutReferenceBookItemBinding;


public class ReferenceBookAdapter extends RecyclerView.Adapter<ReferenceBookAdapter.ReferenceBookViewHolder> {

    private ArrayList<ReferenceBookEntity> mReferenceBookEntities;

    public ReferenceBookAdapter(){

    }

    @NonNull
    @Override
    public ReferenceBookAdapter.ReferenceBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_reference_book_item, parent, false);
        return new ReferenceBookAdapter.ReferenceBookViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReferenceBookAdapter.ReferenceBookViewHolder holder, int position) {

        holder.binding.txtBookName.setText(mReferenceBookEntities.get(position).getBookName());

    }

    @Override
    public int getItemCount() {
        if (mReferenceBookEntities != null) {
            return mReferenceBookEntities.size();
        }
        return 0;
    }

    public static class ReferenceBookViewHolder extends RecyclerView.ViewHolder{

       private LayoutReferenceBookItemBinding binding;

        public ReferenceBookViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void setReferenceBook(ArrayList<ReferenceBookEntity> bookEntities){
        this.mReferenceBookEntities = bookEntities;
        notifyDataSetChanged();
    }
}