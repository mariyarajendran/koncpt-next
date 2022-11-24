package app.technotech.koncpt.ui.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.ExamResultResponseModel;
import app.technotech.koncpt.databinding.DialogResultExamBinding;
import app.technotech.koncpt.ui.activity.MainActivity;

public class ResultExamDialogFragment extends DialogFragment {

    private DialogResultExamBinding binding;
    //    private OnReviewExam reviewExam;
    private ExamResultResponseModel responseModel;

    private String quiz_id;
    private int destination;
    private String userId;


    private String[] correctAnswer;
    private String[] wrongAnswer;
    private String[] notAttempt;

//    public ResultExamDialogFragment(OnReviewExam reviewExam, String resultResponse) {
//        super();
//        this.reviewExam = reviewExam;
//        responseModel = new Gson().fromJson(resultResponse, new TypeToken<ExamResultResponseModel>() {
//        }.getType());
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            responseModel = new Gson().fromJson(getArguments().getString("response"), new TypeToken<ExamResultResponseModel>() {
            }.getType());

            userId = getArguments().getString("user_id");
            quiz_id = getArguments().getString("quiz_id");
            destination = getArguments().getInt("destination");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_result_exam, container, false);
        binding.setLifecycleOwner(getActivity());

        return binding.getRoot();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialogResult);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        List<Integer> correctAnswer = responseModel.getRecord().getRecord().getCorrectAnswerQuestions();
        List<Integer> wrongAnswer = responseModel.getRecord().getRecord().getWrongAnswerQuestions();
        List<Integer> notAttempt = responseModel.getRecord().getRecord().getNotAnsweredQuestions();


        String stringOne = correctAnswer.size() + " Correct";
        String stringTwo = wrongAnswer.size() + " Incorrect";
        String stringNotAttempt = notAttempt.size() + " Not attempted";

        binding.textViewCorrect.setText(stringOne);
        binding.textViewNotCorrect.setText(stringTwo);
        binding.textViewNotAttemped.setText(stringNotAttempt);


        if (responseModel.getRecord().getRecord().getExamStatus().equals("pass")) {
            binding.txtRattingText.setText("Congratulation !");
            binding.txtRattingText.setTextColor(Color.parseColor("#9bcb40"));
        } else {
            binding.txtRattingText.setText("Fail !");
            binding.txtRattingText.setTextColor(Color.RED);
        }


        String obtain_marks = responseModel.getRecord().getRecord().getMarksObtained().toString();
        String total_marks = responseModel.getRecord().getRecord().getTotalMarks();





        Integer data = (int) Math.abs(responseModel.getRecord().getRecord().getPercentage());

        @SuppressLint("DefaultLocale") String percentages = String.format("%.2f", responseModel.getRecord().getRecord().getPercentage());

        String score = "Your score was " + percentages + " %";
        String solved_message = "You solved " + responseModel.getRecord().getQuiz().getTotalQuestions() + " highly yield MCQs and got " + percentages + " %" + " Correct"+
                "\n\n" + "You have obtain " + obtain_marks + " Marks " + " out of total " + total_marks + " Marks" ;

        binding.txtPercentage.setText(score);
        binding.txtPercentageMsg.setText(solved_message);


        if (responseModel.getRecord().getRecord().getPercentage() > 0) {
            binding.seek.setCurProcess(data);
            binding.textview.setText(percentages + "%");
        } else {
            binding.seek.setCurProcess(0);
            binding.textview.setText("0");
        }


        binding.imgCloseRattingDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();

                NavController navController = ((MainActivity) requireActivity()).getmNavController();
                navController.navigate(R.id.action_ResultExamDialog_to_testFragment);

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


        NavController navController = ((MainActivity) requireActivity()).getmNavController();

        Bundle bundle = new Bundle();
        bundle.putString("user_id", userId);
        bundle.putString("quiz_id", quiz_id);
        bundle.putInt("destination", destination);
        navController.navigate(R.id.action_ResultExamDialog_to_reviewQuestionsFragment, bundle);

//        reviewExam.reviewExam();

    }


    public interface OnReviewExam {
        void reviewExam();
    }
}
