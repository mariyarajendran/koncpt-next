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
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.databinding.QbankLevelItemBinding;
import app.technotech.koncpt.ui.callbacks.BuyNowCallbacks;
import app.technotech.koncpt.utils.TextUtil;

public class QBankLevelAdapter extends RecyclerView.Adapter<QBankLevelAdapter.ViewHolder> {

    final Context context;
    final List<BuyDetailsModel.Data> moduleDatumList;
    BuyNowCallbacks buyNowCallbacks;

    public QBankLevelAdapter(Context context, List<BuyDetailsModel.Data> moduleDatumList, BuyNowCallbacks buyNowCallbacks) {
        this.context = context;
        this.moduleDatumList = moduleDatumList;
        this.buyNowCallbacks = buyNowCallbacks;
    }

    @NonNull
    @Override
    public QBankLevelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qbank_level_item, parent, false);
        return new QBankLevelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QBankLevelAdapter.ViewHolder holder, int position) {
        BuyDetailsModel.Data data = moduleDatumList.get(position);
        holder.binding.txtSubjectName.setText(TextUtil.cutNull(data.getLevel_name()));
        holder.binding.cardQbankLevel.setOnClickListener(view -> buyNowCallbacks.onBuy(position));
        if (data.getLevel_active() == 1) {
            holder.binding.imgPro.setVisibility(View.GONE);
        } else {
            holder.binding.imgPro.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return moduleDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private QbankLevelItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

