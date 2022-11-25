package app.technotech.koncpt.ui.adapter.custommoduleadapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomModuleTagAdapter extends RecyclerView.Adapter<CustomModuleTagAdapter.customModuleTagViewHolder>{

    @NonNull
    @Override
    public customModuleTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull customModuleTagViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class  customModuleTagViewHolder extends RecyclerView.ViewHolder{

        public customModuleTagViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
