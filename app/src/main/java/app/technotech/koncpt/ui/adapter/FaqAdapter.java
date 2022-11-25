package app.technotech.koncpt.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.FaqModel;
import app.technotech.koncpt.utils.ExpandableTextView;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {

    Context context;
    List<FaqModel.Faqdatum> moduleDatumList;
    String status,plan;

    public FaqAdapter(Context context, List<FaqModel.Faqdatum> moduleDatumList) {
        this.context = context;
        this.moduleDatumList = moduleDatumList;
    }


    @NonNull
    @Override
    public FaqAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqAdapter.ViewHolder holder, int position) {
        FaqModel.Faqdatum data = moduleDatumList.get(position);
        holder.txtTitle.setText(data.getQuestion());
        holder.description_text.setText(Html.fromHtml(data.getAnswer()));

    }

    @Override
    public int getItemCount() {
        if (moduleDatumList != null) {
            return moduleDatumList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ExpandableTextView description_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.txtTitle);
            description_text=(ExpandableTextView)itemView.findViewById(R.id.description_text);

        }
    }
}
