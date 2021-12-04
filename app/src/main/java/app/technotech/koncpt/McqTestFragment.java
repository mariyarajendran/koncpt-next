package app.technotech.koncpt;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.data.local.entites.QuestionDetailsEntity;
import app.technotech.koncpt.data.network.model.MCQQuestionResponse;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.data.network.model.QuestionsResultResponse;
import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.databinding.FragmentMcqTestBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.viewpageradapter.QuestionViewPagerAdapter;
import app.technotech.koncpt.ui.dialogs.FinishExamDialogFragment;
import app.technotech.koncpt.ui.viewmodels.MCQsViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class McqTestFragment extends Fragment implements View.OnTouchListener, QuestionFragment.OnUpdateMarks, FinishExamDialogFragment.onDialogFinish {



    private FragmentMcqTestBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private MCQsViewModel model;
    public MCQQuestionResponse mCQSData = new MCQQuestionResponse();

    private List<String> arr_correctAnswers = new ArrayList<>();
    private List<String> arr_inCorrectAnswer = new ArrayList<>();
    private List<String> arr_unAttemptQuestion = new ArrayList<>();

    ArrayList<QuestionDetailsEntity> questionEntities = new ArrayList<>();
    private SubjectModel.ModuleDatum data;
    private View mView;


    public static McqTestFragment instance;

    private int correctMarks = 0;
    private int destination;
    private boolean isFinish = false;

    String unAttempt;
    String correct;
    String incorrect;

    private BottomNavigationView bottomNavigationView;

    public QuestionViewPagerAdapter mPagerAdapter;
    private int couuntMarks = 0;


    public static McqTestFragment getInstance() {
        return instance;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        Bundle bundle = getArguments();

        if (bundle != null) {
            data = bundle.getParcelable("data");
            destination = bundle.getInt("destination");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mcq_test, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(MCQsViewModel.class);
        binding.setMcqViewModel(model);

        return binding.getRoot();
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        mView = view;
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();

        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        binding.viewPagerQuestion.setOnTouchListener(this); // disable viewPager swipe-Up
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (binding.viewPagerQuestion.getCurrentItem() < Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount() - 1) {


                    binding.viewPagerQuestion.setCurrentItem(binding.viewPagerQuestion.getCurrentItem() + 1);
                    binding.progressCount.setProgress(binding.viewPagerQuestion.getCurrentItem() + 1);
                    binding.txtQuestionCount.setText("Question " + (binding.viewPagerQuestion.getCurrentItem() + 1) + " of " + Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount());
                    binding.txtCurrentCount.setText("Question No." + (binding.viewPagerQuestion.getCurrentItem() + 1));

                    if (questionEntities.get(binding.viewPagerQuestion.getCurrentItem()).getmBookmarkStatus() == 0) {
                        binding.imageButtonBookmark.setImageResource(R.drawable.icon_bottom_sheet_book_mark_white);
                    } else {
                        binding.imageButtonBookmark.setImageResource(R.drawable.bookmark);
                    }


                } else {

                    calculateQbankData();
                }
            }
        });


        binding.btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.viewPagerQuestion.getCurrentItem() > 0) {
                    binding.progressCount.setProgress(binding.viewPagerQuestion.getCurrentItem());
                    binding.txtCurrentCount.setText("Question No." + (binding.viewPagerQuestion.getCurrentItem()));
                    binding.txtQuestionCount.setText("Question " + (binding.viewPagerQuestion.getCurrentItem()) + " of " + Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount());
                    binding.viewPagerQuestion.setCurrentItem(binding.viewPagerQuestion.getCurrentItem() - 1);
                    if (questionEntities.get(binding.viewPagerQuestion.getCurrentItem()).getmBookmarkStatus() == 0) {
                        binding.imageButtonBookmark.setImageResource(R.drawable.icon_bottom_sheet_book_mark_white);
                    } else {
                        binding.imageButtonBookmark.setImageResource(R.drawable.bookmark);
                    }

                }
            }
        });


        binding.imageButtonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiBookmark(Long.toString(questionEntities.get(binding.viewPagerQuestion.getCurrentItem()).getQuestionId()));
            }
        });


    }

    private void calculateQbankData() {

        arr_unAttemptQuestion = new ArrayList<>();
        arr_correctAnswers = new ArrayList<>();
        arr_inCorrectAnswer = new ArrayList<>();

        unAttempt = "";
        correct = "";
        incorrect = "";

        for (int i = 0; i < questionEntities.size(); i++) {

            if (questionEntities.get(i).ismQuestionAttempt()) {
                if (questionEntities.get(i).getmQuestionState() == 1) {
                    arr_correctAnswers.add(String.valueOf(questionEntities.get(i).getQuestionId()));
                    correctMarks = couuntMarks + questionEntities.get(i).getmQuestionMarks();
                } else if (questionEntities.get(i).getmQuestionState() == 2) {
                    arr_inCorrectAnswer.add(String.valueOf(questionEntities.get(i).getQuestionId()));
                }
            } else {
                arr_unAttemptQuestion.add(String.valueOf(questionEntities.get(i).getQuestionId()));
            }
        }


        unAttempt = TextUtils.join(",", arr_unAttemptQuestion);
        correct = TextUtils.join(",", arr_correctAnswers);
        incorrect = TextUtils.join(",", arr_inCorrectAnswer);

//                    callExamFinishFragment();

//        PauseMCQs();

        callSubmitMCQs();

    }


    private void pauseExamQbank() {


        arr_unAttemptQuestion = new ArrayList<>();
        arr_correctAnswers = new ArrayList<>();
        arr_inCorrectAnswer = new ArrayList<>();

        unAttempt = "";
        correct = "";
        incorrect = "";

        for (int i = 0; i < questionEntities.size(); i++) {

            if (questionEntities.get(i).ismQuestionAttempt()) {
                if (questionEntities.get(i).getmQuestionState() == 1) {
                    arr_correctAnswers.add(String.valueOf(questionEntities.get(i).getQuestionId()));
                    correctMarks = couuntMarks + questionEntities.get(i).getmQuestionMarks();
                } else if (questionEntities.get(i).getmQuestionState() == 2) {
                    arr_inCorrectAnswer.add(String.valueOf(questionEntities.get(i).getQuestionId()));
                }
            } else {
                arr_unAttemptQuestion.add(String.valueOf(questionEntities.get(i).getQuestionId()));
            }
        }


        unAttempt = TextUtils.join(",", arr_unAttemptQuestion);
        correct = TextUtils.join(",", arr_correctAnswers);
        incorrect = TextUtils.join(",", arr_inCorrectAnswer);

        PauseMCQs();

    }


    private void callSubmitMCQs() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("module_id", data.getModuleId());
        params.put("topic_id", data.getId());
        params.put("correct_answer_questions[]", correct);
        params.put("wrong_answer_questions[]", incorrect);
        params.put("not_answered_questions[]", unAttempt);
        params.put("type", "1");
        params.put("total_exam_marks", String.valueOf(mCQSData.getTotal()));
        params.put("user_total_marks", String.valueOf(couuntMarks));
        params.put("pushed_question", "");


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.submiMCQResult(params).observe(getActivity(), new Observer<QuestionsResultResponse>() {
            @Override
            public void onChanged(QuestionsResultResponse questionsResultResponse) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        try {


                            if (questionsResultResponse != null) {
                                if (questionsResultResponse.getStatus() == 1) {
                                    ratingDialog();
                                } else {
                                    Toast.makeText(getActivity(), "" + questionsResultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });


    }


    private void PauseMCQs() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("module_id", data.getModuleId());
        params.put("topic_id", data.getId());
        params.put("correct_answer_questions[]", correct);
        params.put("wrong_answer_questions[]", incorrect);
        params.put("not_answered_questions[]", unAttempt);
        params.put("type", "0");
        params.put("total_exam_marks", String.valueOf(mCQSData.getTotal()));
        params.put("user_total_marks", String.valueOf(couuntMarks));
        params.put("pushed_question", questionEntities.get(binding.viewPagerQuestion.getCurrentItem()).getQuestionId() + "");


        DebugLog.e("Data => " + params.toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.submiMCQResult(params).observe(getActivity(), new Observer<QuestionsResultResponse>() {
            @Override
            public void onChanged(QuestionsResultResponse questionsResultResponse) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        try {
                            if (questionsResultResponse != null) {
                                if (questionsResultResponse.getStatus() == 1) {
                                    Toasty.success(getActivity(), "Sucess").show();
                                } else {
                                    Toast.makeText(getActivity(), "" + questionsResultResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });


    }


    private void ratingDialog() {

        Bundle bundle = new Bundle();
        bundle.putString("topic_id", data.getId());
        bundle.putString("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        bundle.putInt("destination", destination);

        Navigation.findNavController(mView).navigate(R.id.ratingDialogFragment, bundle);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            callApi();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


    private void callApi() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.QbankModuleQuestion.getValue());
        params.put("topic_id", data.getId());

        DebugLog.e("Topics ID => " + data.getId());

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getMCQs(params).observe(getActivity(), new Observer<MCQQuestionResponse>() {
            @Override
            public void onChanged(MCQQuestionResponse mcqQuestionResponse) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (mcqQuestionResponse != null) {
                                if (mcqQuestionResponse.getStatus() == 1) {
                                    mCQSData = mcqQuestionResponse;

                                } else {
                                    Toast.makeText(getActivity(), "" + mcqQuestionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            loadData();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);
            }
        });

    }

    private void loadData() {


        for (int i = 0; i < mCQSData.getData().size(); i++) {

            String dataItem = new Gson().toJson(mCQSData.getData().get(i));


            DebugLog.e("ID => " + mCQSData.getData().get(i).getQuestionFile());


            questionEntities.add(new QuestionDetailsEntity(Long.parseLong(mCQSData.getData().get(i).getId()), mCQSData.getData().get(i).getQuestion(),
                    mCQSData.getData().get(i).getAnswers().get(0).getOptionValue(),
                    mCQSData.getData().get(i).getAnswers().get(1).getOptionValue(),
                    mCQSData.getData().get(i).getAnswers().get(2).getOptionValue(),
                    mCQSData.getData().get(i).getAnswers().get(3).getOptionValue(),
                    mCQSData.getData().get(i).getCorrectAnswers(),
                    dataItem, false, 0, 0, Integer.parseInt(mCQSData.getData().get(i).getId()), 0, mCQSData.getData().get(i).getExplanation(), mCQSData.getData().get(i).getQuestionFile(), "0", mCQSData.getData().get(i).getRefrenceFrom(), mCQSData.getData().get(i).getRefrenceFrom()));

        }

        mPagerAdapter = new QuestionViewPagerAdapter(getParentFragmentManager(), questionEntities);
        binding.viewPagerQuestion.setAdapter(mPagerAdapter);
        binding.viewPagerQuestion.setPagingEnabled(false);


        binding.progressCount.setMax(Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount());
        binding.progressCount.setProgress(binding.viewPagerQuestion.getCurrentItem() + 1);
        binding.txtQuestionCount.setText("Question 1 of " + Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount());
        binding.txtCurrentCount.setText("Question No.1");


        if (questionEntities.get(0).getmBookmarkStatus() == 0) {

            binding.imageButtonBookmark.setImageResource(R.drawable.icon_bottom_sheet_book_mark_white);

        } else {

            binding.imageButtonBookmark.setImageResource(R.drawable.bookmark);
        }


    }

    @Override
    public void updateTotalMarks(int marks) {
        couuntMarks = couuntMarks + marks;

    }

    @Override
    public void updateCorrectQuestions(String question) {
        arr_correctAnswers.add(question);
    }

    @Override
    public void updateWrongQuestions(String question) {
        arr_inCorrectAnswer.add(question);
    }

    @Override
    public void updateUnAttemptQuestions(String question) {
        arr_unAttemptQuestion.add(question);
    }

    @Override
    public void onCompleteExam() {

    }


    public interface onNextClickListener {
        void onNextClick();
    }


    private void apiBookmark(String questionId) {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("item_id", questionId);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.saveBookmarks(params).observe(getActivity(), new Observer<MessageModel>() {
            @Override
            public void onChanged(MessageModel messageModel) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if (messageModel != null) {
                            if (messageModel.getStatus() == 1) {

                                Toasty.success(getActivity(), messageModel.getMessage()).show();

                                questionEntities.get(binding.viewPagerQuestion.getCurrentItem()).setmBookmarkStatus(1);
                                binding.imageButtonBookmark.setImageResource(R.drawable.bookmark);

                            } else {

                                Toasty.success(getActivity(), messageModel.getMessage()).show();

                                questionEntities.get(binding.viewPagerQuestion.getCurrentItem()).setmBookmarkStatus(0);
                                binding.imageButtonBookmark.setImageResource(R.drawable.icon_bottom_sheet_book_mark_white);
                            }
                        }


                    }
                }, 500);

            }
        });


    }

    @Override
    public void onDestroyView() {

        if ((binding.viewPagerQuestion.getCurrentItem() + 1) < questionEntities.size()) {
            pauseExamQbank();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}