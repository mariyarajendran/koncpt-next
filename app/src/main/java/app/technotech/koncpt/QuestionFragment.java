package app.technotech.koncpt;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.technotech.koncpt.data.local.entites.QuestionDetailsEntity;
import app.technotech.koncpt.data.network.model.MCQQuestionResponse;
import app.technotech.koncpt.databinding.FragmentQuestionBinding;
import app.technotech.koncpt.ui.fragments.RatingDialogFragment;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;


public class QuestionFragment extends Fragment {

    private static final String ARG_PARAM = "param";


    long mLastClickTime = 0;

    private FragmentQuestionBinding binding;
    private QuestionDetailsEntity mDetailsEntity;
    private MCQQuestionResponse.Datum datum;
    private OnUpdateMarks updateMarks;
    private String correctAnswer;
    private Context mContext;
    private View mView;


    public McqTestFragment.onNextClickListener clickListener;
    public static McqTestFragment mcqTestFragment;
    //    public McqTestFragment testFragment;
    public static int q_status = 0;
    public static String questionNumber = "";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;

        try {

            updateMarks = (OnUpdateMarks) mcqTestFragment;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance(QuestionDetailsEntity detailsEntity) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, detailsEntity);
        fragment.setArguments(args);
        mcqTestFragment = McqTestFragment.getInstance();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDetailsEntity = (QuestionDetailsEntity) getArguments().getSerializable(ARG_PARAM);
            datum = new Gson().fromJson(mDetailsEntity.getmQuestionData(), new TypeToken<MCQQuestionResponse.Datum>() {
            }.getType());


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;
//11        testFragment = McqTestFragment.getInstance();


        setQuestionData();
        selectedAnswerResult();


        if (!TextUtils.isEmpty(mDetailsEntity.getmExplanation_file()) && !mDetailsEntity.getmExplanation_file().contains("NA")) {

            Glide.with(getActivity())
                    .load(mDetailsEntity.getmExplanation_file())
                    .error(R.drawable.app_logo)
                    .into(binding.imageViewQuestion);

            binding.imageViewQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GeneralUtils.openImageDialog(getActivity(), mDetailsEntity.getmExplanation_file());
                }
            });

            binding.imageViewQuestion.setVisibility(View.VISIBLE);
        }



        if (mDetailsEntity.getCorrectAnswer().equals("1")) {

            correctAnswer = mDetailsEntity.getOptionA();

        } else if (mDetailsEntity.getCorrectAnswer().equals("2")) {

            correctAnswer = mDetailsEntity.getOptionB();

        } else if (mDetailsEntity.getCorrectAnswer().equals("3")) {

            correctAnswer = mDetailsEntity.getOptionC();

        } else if (mDetailsEntity.getCorrectAnswer().equals("4")) {

            correctAnswer = mDetailsEntity.getOptionD();
        }


        binding.txtOptionA.setOnClickListener(v -> {

            if (binding.txtOptionA.getText().toString().equals(correctAnswer)) {
                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(1);
                mDetailsEntity.setMyAnswer("1");
                mDetailsEntity.setmQuestionMarks(Integer.parseInt(datum.getMarks()));

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
                updateMarks.updateWrongQuestions(String.valueOf(datum.getId()));
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(2);
                mDetailsEntity.setMyAnswer("1");
                mDetailsEntity.setmQuestionMarks(0);

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

                if (binding.txtOptionB.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionC.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionD.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }

            explanation();

        });


        binding.txtOptionB.setOnClickListener(v -> {

            if (binding.txtOptionB.getText().toString().equals(correctAnswer)) {
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(1);
                mDetailsEntity.setMyAnswer("2");
                mDetailsEntity.setmQuestionMarks(Integer.parseInt(datum.getMarks()));


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
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(2);
                mDetailsEntity.setMyAnswer("2");
                mDetailsEntity.setmQuestionMarks(0);

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

                if (binding.txtOptionA.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionC.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionD.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }

            explanation();

        });


        binding.txtOptionC.setOnClickListener(v -> {
            if (binding.txtOptionC.getText().toString().equals(correctAnswer)) {
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(1);
                mDetailsEntity.setMyAnswer("3");
                mDetailsEntity.setmQuestionMarks(Integer.parseInt(datum.getMarks()));

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
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(2);
                mDetailsEntity.setMyAnswer("3");
                mDetailsEntity.setmQuestionMarks(0);

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

                if (binding.txtOptionA.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionB.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionD.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }

            explanation();
        });


        binding.txtOptionD.setOnClickListener(v -> {
            if (binding.txtOptionD.getText().toString().equals(correctAnswer)) {
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);

                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(1);
                mDetailsEntity.setMyAnswer("4");
                mDetailsEntity.setmQuestionMarks(Integer.parseInt(datum.getMarks()));

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);

            } else {
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_false);
                mDetailsEntity.setmQuestionAttempt(true);
                mDetailsEntity.setmQuestionState(2);
                mDetailsEntity.setMyAnswer("4");
                mDetailsEntity.setmQuestionMarks(0);

                binding.txtOptionA.setClickable(false);
                binding.txtOptionA.setEnabled(false);
                binding.txtOptionB.setClickable(false);
                binding.txtOptionB.setEnabled(false);
                binding.txtOptionC.setClickable(false);
                binding.txtOptionC.setEnabled(false);
                binding.txtOptionD.setClickable(false);
                binding.txtOptionD.setEnabled(false);
                if (binding.txtOptionA.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionB.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

                if (binding.txtOptionC.getText().toString().equals(correctAnswer)) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }

            }

            explanation();
        });

    }

    private void setQuestionData() {

        if (mDetailsEntity != null) {

            binding.txtQuestion.setText(Html.fromHtml(mDetailsEntity.getQuestion()));
            binding.txtOptionA.setText(mDetailsEntity.getOptionA());
            binding.txtOptionB.setText(mDetailsEntity.getOptionB());
            binding.txtOptionC.setText(mDetailsEntity.getOptionC());
            binding.txtOptionD.setText(mDetailsEntity.getOptionD());


            if (mDetailsEntity.getmExplanation_file() != null) {

            }

        } else {
            DebugLog.e(" Detail entity Empty ");
        }
    }


    public interface OnUpdateMarks {

        void updateTotalMarks(int marks);

        void updateCorrectQuestions(String question);

        void updateWrongQuestions(String question);

        void updateUnAttemptQuestions(String question);
    }


    public void setOnClickListener() {


    }


    private void loadAnotherFragment() {


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new RatingDialogFragment();
        dialogFragment.show(ft, "dialog");


    }

    private void explanation() {


        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        String jsonData = new Gson().toJson(datum);
        Bundle bundle = new Bundle();
        bundle.putString("Data", jsonData);

        Navigation.findNavController(mView).navigate(R.id.questionExplanationBottomFragment, bundle);


    }


    private void selectedAnswerResult() {

        if (!mDetailsEntity.getMyAnswer().equals("0")) {

//        mTxtQuestion.setText((Html.fromHtml(mDetailsEntity.getmQuestion())));
//        mTxtOptionA.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(0).getOptionValue());
//        mTxtOptionB.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(1).getOptionValue());
//        mTxtOptionC.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(2).getOptionValue());
//        mTxtOptionD.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(3).getOptionValue());

            binding.txtOptionA.setClickable(false);
            binding.txtOptionA.setEnabled(false);
            binding.txtOptionB.setClickable(false);
            binding.txtOptionB.setEnabled(false);
            binding.txtOptionC.setClickable(false);
            binding.txtOptionC.setEnabled(false);
            binding.txtOptionD.setClickable(false);
            binding.txtOptionD.setEnabled(false);


            if (mDetailsEntity.getCorrectAnswer().equals(mDetailsEntity.getMyAnswer())) {


                if (mDetailsEntity.getMyAnswer().equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (mDetailsEntity.getMyAnswer().equals("2")) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (mDetailsEntity.getMyAnswer().equals("3")) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (mDetailsEntity.getMyAnswer().equals("4")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }


            } else {

                if (mDetailsEntity.getCorrectAnswer().equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (mDetailsEntity.getCorrectAnswer().equals("2")) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (mDetailsEntity.getCorrectAnswer().equals("3")) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                } else if (mDetailsEntity.getCorrectAnswer().equals("4")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                }


                if (mDetailsEntity.getMyAnswer().equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_false);
                } else if (mDetailsEntity.getMyAnswer().equals("2")) {
                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_false);
                } else if (mDetailsEntity.getMyAnswer().equals("3")) {
                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_false);
                } else if (mDetailsEntity.getMyAnswer().equals("4")) {
                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_false);
                }

            }

        }
    }


}