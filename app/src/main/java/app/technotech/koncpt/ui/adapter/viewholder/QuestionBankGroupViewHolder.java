package app.technotech.koncpt.ui.adapter.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import app.technotech.koncpt.databinding.LayoutQuestionBankListItemGroupBinding;


public class QuestionBankGroupViewHolder extends GroupViewHolder {
//
//    @BindView(R.id.txt_year)
//    TextView mTxtYear;

    public LayoutQuestionBankListItemGroupBinding binding;

    public QuestionBankGroupViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

//    public void setYearTitle(ExpandableGroup expandableGroup) {
//
//        mTxtYear.setText(expandableGroup.getTitle());
//
//    }
}
