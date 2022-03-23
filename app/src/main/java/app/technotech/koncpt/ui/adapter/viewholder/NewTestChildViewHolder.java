package app.technotech.koncpt.ui.adapter.viewholder;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import app.technotech.koncpt.R;
import app.technotech.koncpt.application.GlideApp;
import app.technotech.koncpt.data.network.model.QuestionBank;
import app.technotech.koncpt.databinding.LayoutQuestionBankListItemBinding;
import app.technotech.koncpt.utils.AppSharedPreference;


public class NewTestChildViewHolder extends ChildViewHolder {
    private AppCompatActivity activity;
    private LayoutQuestionBankListItemBinding binding;
    private String levelId;

    public NewTestChildViewHolder(@NonNull View itemView, String levelId) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        this.levelId = levelId;
    }

    public void setSubject(NewTestChildViewHolder holder, AppCompatActivity activity, QuestionBank questionBank, int position) {
        if (questionBank != null) {
            this.activity = activity;
            holder.binding.txtSubjectName.setText(questionBank.getSubjectTitle());
            String moduleString = questionBank.getTotalModule() + " / 16";
            holder.binding.txtModuleCompleted.setText(moduleString);
            GlideApp.with(itemView.getContext())
                    .load(questionBank.getSubjectImage())
                    .override(65)
                    .into(holder.binding.imgSubjectImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("subject_id", questionBank.getSubject_id());
                    bundle.putString("subject_name", questionBank.getSubjectTitle());
                    bundle.putString("level_id", levelId);
                    new AppSharedPreference(itemView.getContext()).saveSubjectIdName(questionBank.getSubject_id(), questionBank.getSubjectTitle());
                    Navigation.findNavController(itemView).navigate(R.id.newTestDetailsFragment, bundle);
                }
            });
        }
    }
}
