package app.technotech.koncpt.ui.adapter.custommoduleadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.QuestionSourceModel;
import app.technotech.koncpt.databinding.LayoutQuestionFromItemBinding;

public class CustomModuleQFromAdapter extends RecyclerView.Adapter<CustomModuleQFromAdapter.customModuleQFromViewHolder>{

    final ArrayList<QuestionSourceModel> mQuestionSourceArrayList;
    final Context context;


    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;


    public CustomModuleQFromAdapter(Context context, ArrayList<QuestionSourceModel> questionSourceArrayList){
        this.context = context;
        this.mQuestionSourceArrayList = questionSourceArrayList;
    }

    @NonNull
    @Override
    public CustomModuleQFromAdapter.customModuleQFromViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_from_item, parent, false);
        return new CustomModuleQFromAdapter.customModuleQFromViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomModuleQFromAdapter.customModuleQFromViewHolder holder, int position) {

        QuestionSourceModel model = mQuestionSourceArrayList.get(position);
        holder.binding.radioBtnSource.setText(model.getSubject_name());
        holder.binding.radioBtnSource.setChecked(model.isSelected());
        holder.binding.radioBtnSource.setTag(new Integer(position));

        if (position == 0 && model.isSelected() && holder.binding.radioBtnSource.isChecked()){
            lastChecked = holder.binding.radioBtnSource;
            lastCheckedPos = 0 ;
        }

        holder.binding.radioBtnSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckBox cb = (CheckBox) view;
                int clickedPos = ((Integer) cb.getTag()).intValue();

                if (cb.isChecked()){
                    if (lastChecked != null){
                        lastChecked.setChecked(false);
                        mQuestionSourceArrayList.get(lastCheckedPos).setSelected(false);
                    }

                    lastChecked = cb;
                    lastCheckedPos = clickedPos;
                } else{
                    lastChecked = null;
                }
                mQuestionSourceArrayList.get(lastCheckedPos).setSelected(cb.isChecked());
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mQuestionSourceArrayList!=null){
            return  mQuestionSourceArrayList.size();
        }
        return 0;
    }

    public static class  customModuleQFromViewHolder extends RecyclerView.ViewHolder {

        private LayoutQuestionFromItemBinding binding;

        public customModuleQFromViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }


    }
}
