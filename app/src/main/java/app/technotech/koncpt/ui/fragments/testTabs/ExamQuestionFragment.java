package app.technotech.koncpt.ui.fragments.testTabs;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.ExamQuestionsModelResponse;
import app.technotech.koncpt.databinding.FragmentExamQuestionBinding;


public class ExamQuestionFragment extends Fragment {

    private ExamQuestionsModelResponse.Question questionItem;
    private FragmentExamQuestionBinding binding;

    public ExamQuestionFragment() {
        // Required empty public constructor
    }

    public static ExamQuestionFragment newInstance(ExamQuestionsModelResponse.Question questionItem) {
        ExamQuestionFragment fragment = new ExamQuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable("data", questionItem);
        fragment.setArguments(args);
        return fragment;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam_question, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.txtQuestion.setText(Html.fromHtml(questionItem.getQuestion()));
        binding.txtOptionA.setText(questionItem.getAnswers().get(0).getOptionValue());
        binding.txtOptionB.setText(questionItem.getAnswers().get(1).getOptionValue());
        binding.txtOptionC.setText(questionItem.getAnswers().get(2).getOptionValue());
        binding.txtOptionD.setText(questionItem.getAnswers().get(3).getOptionValue());

//        questionItem.getQuestionFile()



        if(!TextUtils.isEmpty(questionItem.getQuestionFile()) && !questionItem.getQuestionFile().contains("NA")){

            Glide.with(getActivity())
                    .load(questionItem.getQuestionFile())
                    .error(R.drawable.app_logo)
                    .into(binding.imageViewQuestion);

            binding.imageViewQuestion.setVisibility(View.VISIBLE);
        }



        if (questionItem.isAttemptQuestion()){
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




        clickListner();


    }

    private void clickListner() {


        binding.txtOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.linearOptionA.setBackgroundResource(R.drawable.bg_background_edittext_selected);
                questionItem.setAttemptQuestion(true);
                questionItem.setSelectedAnswer("1");

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

                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext);
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext);


            }
        });

    }
}