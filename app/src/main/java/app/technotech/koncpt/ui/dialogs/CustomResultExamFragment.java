package app.technotech.koncpt.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.DialogResultExamBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;

public class CustomResultExamFragment extends DialogFragment {

    private DialogResultExamBinding binding;
    private final CustomExamModel examModel;
    private final OnReviewExam reviewExam;

    public CustomResultExamFragment(OnReviewExam reviewExam, String resultResponse) {
        this.reviewExam = reviewExam;
        examModel = new Gson().fromJson(resultResponse, new TypeToken<CustomExamModel>() {
        }.getType());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_result_exam, container, false);
        binding.setLifecycleOwner(getActivity());

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtRattingText.setVisibility(View.GONE);


        int sumOfMarks = 0;
        int totalMarks = 0;

        List<CustomExamModel.Datum> questionList = new ArrayList<>();

        questionList = examModel.getData();


        for (int i = 0; i < questionList.size(); i++) {
            DebugLog.e("==>  Marks " + questionList.get(i).getMarks());
            totalMarks = totalMarks + questionList.get(i).getMarks();
        }

        List<String> correctAnswer = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getMyAnswerStatus() == 1) {
                correctAnswer.add(Integer.toString(questionList.get(i).getId()));
                DebugLog.e("==> Attempt Marks " + questionList.get(i).getAttemptMarks());
                sumOfMarks = sumOfMarks + questionList.get(i).getAttemptMarks();
            }
        }

        List<String> wrongAnswer = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getMyAnswerStatus() == 2) {
                wrongAnswer.add(Integer.toString(questionList.get(i).getId()));
            }
        }

        List<String> attemptAnswer = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getMyAnswerStatus() == 0) {
                attemptAnswer.add(Integer.toString(questionList.get(i).getId()));
            }
        }




        float percent = ((float) sumOfMarks / totalMarks) * 100;


        @SuppressLint("DefaultLocale")
        String percentage = String.format("%.2f", percent);



        String stringOne = correctAnswer.size() + " Correct";
        String stringTwo = wrongAnswer.size() + " Incorrect";
        String stringNotAttempt = attemptAnswer.size() + " Not attempted";

        binding.textViewCorrect.setText(stringOne);
        binding.textViewNotCorrect.setText(stringTwo);
        binding.textViewNotAttemped.setText(stringNotAttempt);


//        if (responseModel.getRecord().getRecord().getExamStatus().equals("pass")){
//            binding.txtRattingText.setText("Congratulation !");
//            binding.txtRattingText.setTextColor(Color.parseColor("#9bcb40"));
//        } else {
//            binding.txtRattingText.setText("Condolences !");
//            binding.txtRattingText.setTextColor(Color.RED);
//        }


        String score = "Your score was " + percentage + " %";
        String solved_message = "You solved " + correctAnswer.size() + " highly yield MCQs and got " +percentage + " %" + " Correct";

        binding.txtPercentage.setText(score);
        binding.txtPercentageMsg.setText(solved_message);

        if ((int)percent > 0){
            binding.seek.setCurProcess((int)percent);
            binding.textview.setText(String.valueOf(Integer.toString((int)percent)));
        } else{
            binding.seek.setCurProcess(0);
            binding.textview.setText("0");
        }

        binding.imgCloseRattingDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                new AppSharedPreference(getActivity()).saveHomeScreenData(null);

                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                navigateMe();
            }
        });


    }


    private void navigateMe() {
        dismiss();
        reviewExam.reviewExam();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialogResult);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public interface OnReviewExam {
        void reviewExam();
    }


}
