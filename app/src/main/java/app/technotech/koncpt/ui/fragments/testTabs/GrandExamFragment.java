package app.technotech.koncpt.ui.fragments.testTabs;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.ExamQuestionsModelResponse;
import app.technotech.koncpt.data.network.model.ExamResultResponseModel;
import app.technotech.koncpt.databinding.FragmentGrandExamBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.viewpageradapter.ExamQuestionAdapter;
import app.technotech.koncpt.ui.callbacks.ExamQuestionsCallbacks;
import app.technotech.koncpt.ui.dialogs.FinishExamDialogFragment;
import app.technotech.koncpt.ui.viewmodels.ExamQuestionsViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;


public class GrandExamFragment extends Fragment implements FinishExamDialogFragment.onDialogFinish {


    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private ExamQuestionsViewModel model;
    private FragmentGrandExamBinding binding;
    private MyCountDownTimer myCountDownTimer;
    private ExamQuestionAdapter questionAdapter;
    private ExamQuestionsModelResponse examQuestionsModelResponseData;
    private List<ExamQuestionsModelResponse.Question> questionList = new ArrayList<>();

    private String quiz_id;
    private String results;
    private String userId;
    private String resultResponse;
    private long hours;
    private long minute;
    private long seconds;
    private long spentTime;
    private String notAttempt;
    private int counter = 0;
    private boolean finish = false;
    private int destination;


    private BottomNavigationView bottomNavigationView;

    public GrandExamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quiz_id = getArguments().getString("quiz_id");
            destination = getArguments().getInt("destination");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_grand_exam, container, false);
        binding.setLifecycleOwner(getActivity());
        model = new ViewModelProvider(getActivity()).get(ExamQuestionsViewModel.class);
        binding.setExamViewModel(model);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        userId = String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId());


        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();

        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }



        onCallApi();
        onButtonClickListener();

    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    private void onButtonClickListener() {

        binding.setExamCallbacks(new ExamQuestionsCallbacks() {
            @Override
            public void onPrevious() {

                if (binding.viewPagerQuestion.getCurrentItem() > 0) {
                    String questCount = "Question " + ((binding.viewPagerQuestion.getCurrentItem() + 1) - 1) + " of " + Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount();
                    binding.txtQuestionCount.setText(questCount);
                    String currentCount = "Question No." + ((binding.viewPagerQuestion.getCurrentItem() + 1) - 1);
                    binding.txtCurrentCount.setText(currentCount);
                    binding.viewPagerQuestion.setCurrentItem(binding.viewPagerQuestion.getCurrentItem() - 1);
                    binding.progressCount.setProgress(binding.viewPagerQuestion.getCurrentItem() - 1);
                }

            }

            @Override
            public void onNext() {

                if (binding.viewPagerQuestion.getCurrentItem() < Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount() - 1) {
                    binding.viewPagerQuestion.setCurrentItem(binding.viewPagerQuestion.getCurrentItem() + 1);
                    binding.progressCount.setProgress(binding.viewPagerQuestion.getCurrentItem() + 1);

                    String questCount = "Question " + (binding.viewPagerQuestion.getCurrentItem() + 1) + " of " + Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount();
                    binding.txtQuestionCount.setText(questCount);
                    String currentCount = "Question No." + (binding.viewPagerQuestion.getCurrentItem() + 1);
                    binding.txtCurrentCount.setText(currentCount);
                } else {

                    counter = 0;

                    List<String> data = new ArrayList<>();
                    for (int i = 0; i < questionList.size(); i++) {
                        if (questionList.get(i).isAttemptQuestion()) {
                            data.add("\"" + questionList.get(i).getId() + "\"" + ":" + "\"" + questionList.get(i).getSelectedAnswer() + "\"");
                        } else {
                            counter = counter + 1;
                        }
                    }
                    notAttempt = Integer.toString(counter);
                    callExamFinishFragment();


                }

            }
        });


    }

    private void sendFinishData() {

        List<String> data = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).isAttemptQuestion()) {
                data.add("\"" + questionList.get(i).getId() + "\"" + ":" + "\"" + questionList.get(i).getSelectedAnswer() + "\"");
            } else {
                counter = counter + 1;

            }
        }
        notAttempt = Integer.toString(counter);
        results = TextUtils.join(",", data);
        results = "{" + results + "}";
        spentTime = Long.parseLong(examQuestionsModelResponseData.getQuiz().getDueration()) - minute;


        onFinishExam();

    }

    private void callDialogFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("response", resultResponse);
        bundle.putString("user_id", userId);
        bundle.putString("quiz_id", quiz_id);
        bundle.putInt("destination", destination);

        NavController navController = ((MainActivity) requireActivity()).getmNavController();
        navController.navigate(R.id.ResultExamDialog, bundle);

    }

    private void callExamFinishFragment() {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = requireActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new FinishExamDialogFragment(this, notAttempt);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "dialog");
    }


    private void startTimer() {

        myCountDownTimer = new MyCountDownTimer(Long.parseLong(examQuestionsModelResponseData.getQuiz().getDueration()) * 60000, 1);
        myCountDownTimer.start();

    }

    private void loadData() {

        String questions_counts = "Questions 1 of " + questionList.size();
        binding.txtQuestionCount.setText(questions_counts);
        binding.progressCount.setMax(questionList.size());
        binding.progressCount.setProgress(1);
        binding.txtCurrentCount.setText("Question No.1");

        questionAdapter = new ExamQuestionAdapter(getChildFragmentManager(), 0, questionList);
        binding.viewPagerQuestion.setAdapter(questionAdapter);
        binding.viewPagerQuestion.setPagingEnabled(false);




    }

    @Override
    public void onCompleteExam() {

        myCountDownTimer.cancel();
        sendFinishData();

    }


    private class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            hours = TimeUnit.MILLISECONDS.toHours(millis);
            minute = TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis));
            seconds = TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));

            @SuppressLint("DefaultLocale")
            String hms = String.format("%02d:%02d:%02d", hours, minute, seconds);
            binding.txtTimer.setText(hms);//set text
        }

        @Override
        public void onFinish() {
            binding.txtTimer.setText("TIME'S UP!!"); //On finish change timer text
            sendFinishData();
        }
    }



    private void onCallApi() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("quizid", quiz_id);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getExamQuestions(params).observe(getActivity(), new Observer<ExamQuestionsModelResponse>() {
            @Override
            public void onChanged(ExamQuestionsModelResponse examQuestionsModelResponse) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.hide();
                        }
                        try {

                            if (examQuestionsModelResponse != null) {
                                if (examQuestionsModelResponse.getStatus() == 1) {

                                    examQuestionsModelResponseData = examQuestionsModelResponse;
                                    questionList = examQuestionsModelResponse.getQuestions();

                                    loadData();
                                    startTimer();
                                } else {
                                    Toasty.error(getActivity(), "No Data Found").show();
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


    private void onFinishExam() {

        Map<String, String> params = new HashMap<>();
        params.put("time_spent", String.valueOf(spentTime));
        params.put("quiz_id", quiz_id);
        params.put("student_id", userId);
        params.put("answers", results);

//        DebugLog.e("Params => " + params.toString());

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getExamResult(params).observe(requireActivity(), new Observer<ExamResultResponseModel>() {
            @Override
            public void onChanged(ExamResultResponseModel examResultResponseModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (examResultResponseModel != null) {
                            if (examResultResponseModel.getStatus() == 1) {
                                resultResponse = new Gson().toJson(examResultResponseModel);
                                String jsonData = new Gson().toJson(resultResponse);
                                DebugLog.e("Data = > " + jsonData);
                                callDialogFragment();
                            }
                        }

                    }
                }, 500);

            }
        });

    }


}