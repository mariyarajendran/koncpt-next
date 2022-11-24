package app.technotech.koncpt.ui.fragments.qbank.custommodule;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.data.network.model.MessageModel;
import app.technotech.koncpt.databinding.FragmentCustomExamBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.custommoduleadapters.CustomExamAdapter;
import app.technotech.koncpt.ui.callbacks.ExamQuestionsCallbacks;
import app.technotech.koncpt.ui.dialogs.CustomResultExamFragment;
import app.technotech.koncpt.ui.dialogs.FinishExamDialogFragment;
import app.technotech.koncpt.ui.viewmodels.CustomExamViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class CustomExamFragment extends Fragment implements CustomResultExamFragment.OnReviewExam, FinishExamDialogFragment.onDialogFinish {


    private FragmentCustomExamBinding binding;
    private CustomExamViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private Context mContext;

    private CustomExamAdapter examAdapter;
    private CustomExamModel examModel;
    private List<CustomExamModel.Datum> questionList = new ArrayList<>();

    private int counter = 0;
    private String notAttempt;
    private String correctAnswer;
    private String wrongAnswer;
    private String percentage;
    private String subject_list;
    public static String mode;


    public CustomExamFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            examModel = new Gson().fromJson(getArguments().getString("data"), new TypeToken<CustomExamModel>() {
            }.getType());
            questionList = examModel.getData();
            subject_list = getArguments().getString("subject_list");
            mode = getArguments().getString("mode");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_exam, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(CustomExamViewModel.class);
        binding.setExamViewModel(model);

        return binding.getRoot();
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();

        setHasOptionsMenu(true);


        Toolbar toolbar = ((MainActivity)requireActivity()).getToolbar();

        toolbar.setTitle(mode + " Mode");


        loadData();
        onClickListener();


        binding.imageButtonBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiBookmark(Integer.toString(questionList.get(binding.viewPagerQuestion.getCurrentItem()).getId()));
            }
        });

    }

    private void loadData() {

        if (mode.equals("Regular")) {
            binding.btnPrevious.setVisibility(View.GONE);
            binding.imageButtonBookmark.setVisibility(View.VISIBLE);
        } else {
            binding.btnPrevious.setVisibility(View.VISIBLE);
            binding.imageButtonBookmark.setVisibility(View.GONE);
        }


        String questions_counts = "Questions 1 of " + questionList.size();
        binding.txtQuestionCount.setText(questions_counts);
        binding.progressCount.setMax(questionList.size());
        binding.progressCount.setProgress(1);
        binding.txtCurrentCount.setText("Question No.1");

        examAdapter = new CustomExamAdapter(getChildFragmentManager(), 0, questionList);
        binding.viewPagerQuestion.setAdapter(examAdapter);
        binding.viewPagerQuestion.setPagingEnabled(false);
    }

    private void onClickListener() {

        binding.setExamCallbacks(new ExamQuestionsCallbacks() {
            @Override
            public void onPrevious() {
                if (binding.viewPagerQuestion.getCurrentItem() > 0) {
                    binding.progressCount.setProgress(binding.viewPagerQuestion.getCurrentItem() - 1);
                    String questCount = "Question " + ((binding.viewPagerQuestion.getCurrentItem() + 1) - 1) + " of " + Objects.requireNonNull(binding.viewPagerQuestion.getAdapter()).getCount();
                    binding.txtQuestionCount.setText(questCount);
                    String currentCount = "Question No." + ((binding.viewPagerQuestion.getCurrentItem() + 1) - 1);
                    binding.txtCurrentCount.setText(currentCount);
                    binding.viewPagerQuestion.setCurrentItem(binding.viewPagerQuestion.getCurrentItem() - 1);

                } else {
                    Toasty.info(getActivity(), "You are on top question").show();
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


                    if (questionList.get(binding.viewPagerQuestion.getCurrentItem()).getBookmarkStatus() == 0) {
                        binding.imageButtonBookmark.setImageResource(R.drawable.icon_bottom_sheet_book_mark_white);
                    } else {
                        binding.imageButtonBookmark.setImageResource(R.drawable.bookmark);
                    }


                } else {

                    List<String> attemptAnswer = new ArrayList<>();
                    for (int i = 0; i < questionList.size(); i++) {
                        if (questionList.get(i).getMyAnswerStatus() == 0) {
                            attemptAnswer.add(Integer.toString(questionList.get(i).getId()));
                        }
                    }


                    if (mode.equals("Regular")) {
                        String jsonData = new Gson().toJson(examModel);
                        new AppSharedPreference(getActivity()).saveCustomModule(jsonData);
                        callDialogFragment();
                    } else {
                        callExamFinishFragment(attemptAnswer.size() + "");
                    }


                }
            }
        });

    }


    private void callExamFinishFragment(String notAttempt) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new FinishExamDialogFragment(this::onCompleteExam, notAttempt);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "dialog");
    }


    private void callDialogFragment() {

        String resultResponse = new Gson().toJson(examModel);

        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);


        DialogFragment dialogFragment = new CustomResultExamFragment(this, resultResponse);
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "dialog");


    }

    private void apiBookmark(String questionId) {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.SaveBookMark.getValue());
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
                                binding.imageButtonBookmark.setImageResource(R.drawable.bookmark);

                            } else {
                                Toasty.success(getActivity(), messageModel.getMessage()).show();
                                binding.imageButtonBookmark.setImageResource(R.drawable.icon_bottom_sheet_book_mark_white);
                            }
                        }


                    }
                }, 500);

            }
        });


    }

    @Override
    public void onCompleteExam() {
        String jsonData = new Gson().toJson(examModel);
        new AppSharedPreference(getActivity()).saveCustomModule(jsonData);
        callDialogFragment();
    }

    @Override
    public void reviewExam() {
        Navigation.findNavController(binding.getRoot()).navigate(R.id.customReviewModule);
    }
}