package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.krita.koncpt.ui.callbacks.BuyNowCallbacks;
import app.krita.koncpt.utils.TextUtil;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.databinding.RowBuyDetailItemBinding;

public class BuyDetailsAdapter extends RecyclerView.Adapter<BuyDetailsAdapter.ViewHolder> {

    final Context context;
    final List<BuyDetailsModel.Data> moduleDatumList;
    BuyNowCallbacks buyNowCallbacks;

    public BuyDetailsAdapter(Context context, List<BuyDetailsModel.Data> moduleDatumList, BuyNowCallbacks buyNowCallbacks) {
        this.context = context;
        this.moduleDatumList = moduleDatumList;
        this.buyNowCallbacks = buyNowCallbacks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_buy_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BuyDetailsModel.Data data = moduleDatumList.get(position);
        String label = data.getLabel();
        String questions = String.valueOf(data.getNumberOfQuestion());
        String subjects = String.valueOf(data.getNumberOfSubjectOrTest());
        Double value1 = Double.parseDouble(questions);
        Double value2 = Double.parseDouble(subjects);
        Double calculatedValue = value2 * value1;
        holder.binding.txtLabel.setText(label);
        holder.binding.txtQuestions.setText(questions + "\tQuestions,");
        holder.binding.txtSubjects.setText(subjects + "\tSubjects with explaination");
        holder.binding.txtMcq.setText(questions + "*" + subjects + "=" + String.valueOf(calculatedValue) + "MCQs");
        holder.binding.btnBuyNowItem.setOnClickListener(view -> buyNowCallbacks.onBuy(position));
        holder.binding.btnBuyNowItem.setText(String.format("₹" + TextUtil.cutNull(data.getAmount()) + "%s", context.getApplicationContext().getResources().getString(R.string.message_buy)));
        if (data.getLevel_active() == 1) {
            holder.binding.btnBuyNowItem.setClickable(false);
            holder.binding.btnBuyNowItem.setBackground(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.drawable_button_grey));
        } else {
            holder.binding.btnBuyNowItem.setClickable(true);
            holder.binding.btnBuyNowItem.setBackground(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.drawable_button_green));
        }
    }

    @Override
    public int getItemCount() {
        return moduleDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowBuyDetailItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
