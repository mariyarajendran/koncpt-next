package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.FragmentCustomQuestionBinding;


public class CustomQuestionFragment extends Fragment {

    private FragmentCustomQuestionBinding binding;
    private CustomExamModel.Datum questionItem;
    private String correctAnswer;
    private long mLastClickTime = 0;


    public CustomQuestionFragment() {
        // Required empty public constructor
    }


    public static CustomQuestionFragment getInstance(CustomExamModel.Datum examData) {

        CustomQuestionFragment questionFragment = new CustomQuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", examData);
        questionFragment.setArguments(bundle);
        return questionFragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            questionItem = getArguments().getParcelable("data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_question, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        if (CustomExamFragment.mode.equals("Exam")){
            binding.txtQuestion.setText(Html.fromHtml(questionItem.getQuestion()));
            binding.txtOptionA.setText(questionItem.getAnswers().get(0).getOptionValue());
            binding.txtOptionB.setText(questionItem.getAnswers().get(1).getOptionValue());
            binding.txtOptionC.setText(questionItem.getAnswers().get(2).getOptionValue());
            binding.txtOptionD.setText(questionItem.getAnswers().get(3).getOptionValue());


            if (questionItem.isAttemptQuestion()) {

                if (Integer.parseInt(questionItem.getSelectedAnswer()) == 1) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                } else if (Integer.parseInt(questionItem.getSelectedAnswer()) == 2) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                } else if (Integer.parseInt(questionItem.getSelectedAnswer()) == 3) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                } else if (Integer.parseInt(questionItem.getSelectedAnswer()) == 4) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                }

            }


            clickListener();

        } else if (CustomExamFragment.mode.equals("Regular")){

            if (questionItem.getCorrectAnswers().equals("1")) {

                correctAnswer = questionItem.getCorrectAnswers();

            } else if (questionItem.getCorrectAnswers().equals("2")) {

                correctAnswer = questionItem.getCorrectAnswers();

            } else if (questionItem.getCorrectAnswers().equals("3")) {

                correctAnswer = questionItem.getCorrectAnswers();

            } else if (questionItem.getCorrectAnswers().equals("4")) {

                correctAnswer = questionItem.getCorrectAnswers();
            }

            mcqAnswer();


        }






    }

    private void mcqAnswer() {




        binding.txtOptionA.setOnClickListener(v -> {


            if (correctAnswer.equals("1")) {
                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(1);
                questionItem.setAttemptMarks(questionItem.getMarks());
                questionItem.setSelectedAnswer("1");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);


            } else {

                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_false);
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(2);
                questionItem.setAttemptMarks(0);
                questionItem.setSelectedAnswer("1");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

                if (correctAnswer.equals("2")) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("3")) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("4")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }

            explanation();

        });


        binding.txtOptionB.setOnClickListener(v -> {

//            if (binding.txtOptionB.getText().toString().equals(correctAnswer)) {
            if (correctAnswer.equals("2")) {
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(1);
                questionItem.setAttemptMarks(questionItem.getMarks());
                questionItem.setSelectedAnswer("2");


                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);



            } else {
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_false);
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(2);
                questionItem.setAttemptMarks(0);
                questionItem.setSelectedAnswer("2");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);


                if (correctAnswer.equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("3")) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("4")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }
            explanation();
        });


        binding.txtOptionC.setOnClickListener(v -> {
//            if (binding.txtOptionC.getText().toString().equals(correctAnswer)) {
            if (correctAnswer.equals("3")) {
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(1);
                questionItem.setAttemptMarks(questionItem.getMarks());
                questionItem.setSelectedAnswer("3");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

            } else {
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_false);
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(2);
                questionItem.setAttemptMarks(0);
                questionItem.setSelectedAnswer("3");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);


                if (correctAnswer.equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("2")) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("4")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }
            explanation();
        });


        binding.txtOptionD.setOnClickListener(v -> {
//            if (binding.txtOptionD.getText().toString().equals(correctAnswer)) {
            if (correctAnswer.equals("4")) {
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);

                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(1);
                questionItem.setAttemptMarks(questionItem.getMarks());
                questionItem.setSelectedAnswer("4");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);


            } else {
                questionItem.setAttemptQuestion(true);
                questionItem.setMyAnswerStatus(2);
                questionItem.setAttemptMarks(0);
                questionItem.setSelectedAnswer("4");

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

                if (correctAnswer.equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("2")) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (correctAnswer.equals("3")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }
            explanation();
        });






    }

    private void explanation() {


        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();


        String jsonData = new Gson().toJson(questionItem);
        Bundle bundle = new Bundle();
        bundle.putString("Data", jsonData);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.questionExplanationBottomFragment, bundle);


    }

    private void clickListener() {


        binding.txtOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.linearOptionA.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                questionItem.setAttemptQuestion(true);
                questionItem.setSelectedAnswer("1");

                if (questionItem.getCorrectAnswers().equals("1")){
                    questionItem.setAttemptMarks(questionItem.getMarks());
                    questionItem.setMyAnswerStatus(1);
                } else {
                    questionItem.setAttemptMarks(0);
                    questionItem.setMyAnswerStatus(2);
                }


                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext);


            }
        });

        binding.txtOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.linearOptionB.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                questionItem.setAttemptQuestion(true);
                questionItem.setSelectedAnswer("2");



                if (questionItem.getCorrectAnswers().equals("2")){
                    questionItem.setAttemptMarks(questionItem.getMarks());
                    questionItem.setMyAnswerStatus(1);
                } else {
                    questionItem.setAttemptMarks(0);
                    questionItem.setMyAnswerStatus(2);
                }


                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext);


            }
        });


        binding.txtOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.linearOptionC.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                questionItem.setAttemptQuestion(true);
                questionItem.setSelectedAnswer("3");



                if (questionItem.getCorrectAnswers().equals("3")){
                    questionItem.setAttemptMarks(questionItem.getMarks());
                    questionItem.setMyAnswerStatus(1);
                } else {
                    questionItem.setAttemptMarks(0);
                    questionItem.setMyAnswerStatus(2);
                }


                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext);


            }
        });

        binding.txtOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.linearOptionD.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                questionItem.setAttemptQuestion(true);
                questionItem.setSelectedAnswer("4");



                if (questionItem.getCorrectAnswers().equals("4")){
                    questionItem.setAttemptMarks(questionItem.getMarks());
                    questionItem.setMyAnswerStatus(1);
                } else {
                    questionItem.setAttemptMarks(0);
                    questionItem.setMyAnswerStatus(2);
                }

                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext);


            }
        });

    }
}
