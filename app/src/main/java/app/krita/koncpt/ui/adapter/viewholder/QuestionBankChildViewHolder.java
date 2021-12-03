package app.technotech.koncpt.ui.adapter.viewholder;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.QuestionBank;
import app.technotech.koncpt.databinding.LayoutQuestionBankListItemBinding;
import app.technotech.koncpt.utils.AppSharedPreference;


public class QuestionBankChildViewHolder extends ChildViewHolder {

//    @BindView(R.id.img_subject_image)
//    CircularImageView mImgSubject;
//
//    @BindView(R.id.txt_subject_name)
//    TextView mTxtSubjectName;
//
//    @BindView(R.id.txt_module_completed)
//    TextView mTxtModuleCompleted;


    private AppCompatActivity activity;

    private LayoutQuestionBankListItemBinding binding;

    public QuestionBankChildViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void setSubject(QuestionBankChildViewHolder holder, AppCompatActivity activity, QuestionBank questionBank, int position) {

        if (questionBank != null) {

            this.activity = activity;


            holder.binding.txtSubjectName.setText(questionBank.getSubjectTitle());
            String moduleString = questionBank.getTotalModule() + " / 16";
            holder.binding.txtModuleCompleted.setText(moduleString);


            Glide.with(itemView.getContext())
                    .load(questionBank.getSubjectImage())
                    .override(65)
                    .into(holder.binding.imgSubjectImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Bundle bundle = new Bundle();
                    bundle.putString("subject_id", questionBank.getId());
                    bundle.putString("subject_name", questionBank.getSubjectTitle());

                    new AppSharedPreference(itemView.getContext()).saveSubjectIdName(questionBank.getId(), questionBank.getSubjectTitle());

                    Navigation.findNavController(itemView).navigate(R.id.subjectDetailsFragment, bundle);

                }
            });


        }
    }


}
