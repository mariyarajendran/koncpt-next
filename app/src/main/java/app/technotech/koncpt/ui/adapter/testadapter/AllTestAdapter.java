package app.technotech.koncpt.ui.adapter.testadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.TestEntity;
import app.technotech.koncpt.databinding.LayoutTestItemBinding;
import app.technotech.koncpt.ui.interfaces.TestListener;


public class AllTestAdapter extends RecyclerView.Adapter<AllTestAdapter.TestViewHolder> {

    private ArrayList<TestEntity> mTestEntities;

    private static TestListener mListener;

    public AllTestAdapter(TestListener testListener){
        this.mListener = testListener;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_test_item, parent, false);
        return new AllTestAdapter.TestViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.onBind(holder, mTestEntities.get(position));
    }

    @Override
    public int getItemCount() {
        if(mTestEntities!=null){
            return  mTestEntities.size();
        }
        return 0;
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.txt_test_title)
//        TextView mTxtTestTitle;
//
//        @BindView(R.id.txt_test_pro)
//        TextView mTxtTestPro;


        private LayoutTestItemBinding binding;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }

        void onBind(TestViewHolder holder, TestEntity testEntity) {

            holder.binding.txtTestTitle.setText(testEntity.getTestTitle());
            if (testEntity.ismTestIsPro()) {
                holder.binding.txtTestPro.setVisibility(View.VISIBLE);
            }

            itemView.setOnClickListener(v -> mListener.onTestSelectListener(testEntity));

        }
    }

    public void setTestData(ArrayList<TestEntity> testEntities){
        this.mTestEntities = testEntities;
        notifyDataSetChanged();
    }
}
