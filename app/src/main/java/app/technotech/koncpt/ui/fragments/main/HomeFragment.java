package app.technotech.koncpt.ui.fragments.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.BuildConfig;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.AnswerMcqOfTheDayModel;
import app.technotech.koncpt.data.network.model.HomeScreenModel;
import app.technotech.koncpt.databinding.FragmentHomeBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.SuggestedCourseAdapter;
import app.technotech.koncpt.ui.adapter.SuggestedMaterClassAdapter;
import app.technotech.koncpt.ui.adapter.SuggestedQuestionBankAdapter;
import app.technotech.koncpt.ui.dialogs.SendOTPDialogFragment;
import app.technotech.koncpt.ui.viewmodels.HomeScreenViewModel;
import app.technotech.koncpt.ui.viewmodels.HomeViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment implements SuggestedCourseAdapter.OnTestItemClicked, SuggestedQuestionBankAdapter.OnMcqItemSelected, SuggestedMaterClassAdapter.OnMasterSelect, View.OnTouchListener {


    private FragmentHomeBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private HomeScreenViewModel model;

    private HomeScreenModel homeScreenData;

    private int mcqAnswer = -1;



    private HomeViewModel mViewModel;
    private Context mContext;

    private SuggestedCourseAdapter mCourseAdapter;
    private SuggestedMaterClassAdapter mMasterClassAdapter;
    private SuggestedQuestionBankAdapter mQuestionBankAdapter;

    private BottomNavigationView bottomNavigationView;
    private CircularImageView headerImageView;
    private AppSharedPreference sharedPreference;
    private NavController navController;

    private View mView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;

        DebugLog.e("OnAttached Method Call ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DebugLog.e("OnCreate Method Call");

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DebugLog.e("OnCreateView Method Call");

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(HomeScreenViewModel.class);
        binding.setHomeViewModel(model);

        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        init();


    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    @Override
    public void onStart() {
        super.onStart();
        DebugLog.e("OnStart Method Call" + sharedPreference.getSavedToken());

        String hash = GeneralUtils.getMd5Hash(BuildConfig.APPLICATION_ID);
        DebugLog.e("Key => " + hash);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        DebugLog.e("OnActivityCreated Method Call");

        if (sharedPreference.getHomeScreenData() != null) {
            homeScreenData = sharedPreference.getHomeScreenData();
            loadData();
        } else {
            initAPiCall();
        }


    }

    private void init() {

        utils = new GeneralUtils((MainActivity) requireActivity());
        progressDialog = utils.showProgressDialog();
        navController = ((MainActivity) requireActivity()).getmNavController();
        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        headerImageView = ((MainActivity) requireActivity()).getCircularImage();
        sharedPreference = utils.getAppSharedPreference();


        if (bottomNavigationView.getVisibility() == View.GONE) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }




        binding.revSuggestedVideo.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        binding.revSuggestedCourse.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        binding.revSuggestedQBank.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));

        DebugLog.e("ID = > " + Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));


        searchDatas();


        String json = new Gson().toJson(new AppSharedPreference(getActivity()).getUserResponse());
        DebugLog.e("Data => " + json);
        DebugLog.e("Data => " + json);
        DebugLog.e("Data => " + json);



    }

    private void searchDatas() {


        binding.searchViewFileHash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        binding.viewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchViewFileHash.setIconified(false);
                binding.searchViewFileHash.clearFocus();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.searchHomeFragment);

//                throw new RuntimeException("Test Crash"); // Force a crash

            }
        });

    }

    private void initAPiCall() {


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        Map<String, String> params = new HashMap<>();


        if (!new AppSharedPreference(mContext).getMcqOfTheDay().equals("0")) {

            params.put(EnumApiAction.action.getValue(), EnumApiAction.HomeScreen.getValue());
            params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
            params.put("mcq_of_the_day_question_id", new AppSharedPreference(mContext).getMcqOfTheDay());


        } else {
            params.put(EnumApiAction.action.getValue(), EnumApiAction.HomeScreen.getValue());
            params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
            params.put("mcq_of_the_day_question_id", "");


        }


        model.getHoeScreenData(params).observe(getActivity(), new Observer<HomeScreenModel>() {
            @Override
            public void onChanged(HomeScreenModel homeScreenModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (homeScreenModel != null) {

                                if (homeScreenModel.getStatus() == 1) {

                                    homeScreenData = homeScreenModel;

                                    loadData();
                                    sharedPreference.saveHomeScreenData(new Gson().toJson(homeScreenModel));


                                } else {

                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    Toasty.error(mContext, homeScreenModel.getMessage()).show();
                                }
                            } else {

                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toasty.error(mContext, "Something went wrong").show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }


                    }
                }, 1000);

            }
        });


    }

    private void loadData() {


        try {






//        String strLiveClass = "┃ Only live classes ┃ Only live classes ┃ Only live classes ┃";//❚
//            String strLiveClass = homeScreenData.getData().getLiveClass().getClassMarques();//❚
//            Spanned spannableString = Html.fromHtml(strLiveClass);
//            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.btn_yellow)), 0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.btn_yellow)), 20,22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.btn_yellow)), 40,42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.btn_yellow)), 60,61, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


//            binding.txtLiveClass.setSelected(true);
//            binding.txtLiveClass.setText(spannableString);
//

            if (!TextUtils.isEmpty(homeScreenData.getData().getLiveClass().getClassMarques()) && homeScreenData.getData().getLiveClass().getClassMarques() != null){
                loadLiveView(homeScreenData.getData().getLiveClass().getClassMarques());
            }


            Glide.with(getActivity())
                    .load(sharedPreference.getUserResponse().getImage())
                    .error(R.drawable.ic_default)
                    .into(headerImageView);

            if (homeScreenData.getData().getMcqOfTheDay().size() > 0) {
                binding.cardMcq.setVisibility(View.VISIBLE);
                binding.txtMcqLabel.setVisibility(View.VISIBLE);

                new AppSharedPreference(mContext).setMcqOfTheDayId(Integer.toString(homeScreenData.getData().getMcqOfTheDay().get(0).getId()));

            } else {
                binding.cardMcq.setVisibility(View.GONE);
            }


            if (homeScreenData.getData().getMcqOfTheDay().size() > 0) {
                if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().size() > 0) {

                    selectedAnswerResult();

                } else {

                    binding.txtQuestion.setText(Html.fromHtml(homeScreenData.getData().getMcqOfTheDay().get(0).getQuestion()));
                    binding.txtOptionA.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(0).getOptionValue());
                    binding.txtOptionB.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(1).getOptionValue());
                    binding.txtOptionC.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(2).getOptionValue());
                    binding.txtOptionD.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(3).getOptionValue());

                    clickListener();
                }


                if (homeScreenData.getData().getMcqOfTheDay().get(0).getQuestionFile().contains("jpeg") ||
                        homeScreenData.getData().getMcqOfTheDay().get(0).getQuestionFile().contains("jpg") ||
                        homeScreenData.getData().getMcqOfTheDay().get(0).getQuestionFile().contains("png")) {


                    Glide.with(getActivity())
                            .load(homeScreenData.getData().getMcqOfTheDay().get(0).getQuestionFile())
                            .error(R.drawable.app_logo)
                            .into(binding.imageMcqOfTheDay);



                    binding.imageMcqOfTheDay.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            GeneralUtils.openImageDialog(getActivity(),homeScreenData.getData().getMcqOfTheDay().get(0).getQuestionFile());
                        }
                    });
                    binding.imageMcqOfTheDay.setVisibility(View.VISIBLE);

                } else {
                    binding.imageMcqOfTheDay.setVisibility(View.GONE);
                }

            }


            if (homeScreenData.getData().getMcqAnswerStatus().getIsAnswerForMcq() != 0) {
                binding.btnExplanation.setVisibility(View.VISIBLE);
            } else {
                binding.btnExplanation.setVisibility(View.GONE);
            }

            binding.btnExplanation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigateExplanation();
                }
            });


            binding.progressModule.setProgress(homeScreenData.getData().getProgress().getProgressPercentange());
            binding.txtProgressPercentage.setText(homeScreenData.getData().getProgress().getProgressPercentange() + "%");



            if (homeScreenData.getData().getTestSeries().size() > 0) {

                binding.revSuggestedCourse.setVisibility(View.VISIBLE);
                binding.txtLabelSuggestedCourse.setVisibility(View.VISIBLE);


                mCourseAdapter = new SuggestedCourseAdapter(homeScreenData.getData().getTestSeries(), mContext, this);
                binding.revSuggestedCourse.setAdapter(mCourseAdapter);


            } else {
                binding.revSuggestedCourse.setVisibility(View.GONE);
                binding.txtLabelSuggestedCourse.setVisibility(View.GONE);
            }


            if (homeScreenData.getData().getPopularMcq().size() > 0) {

                binding.revSuggestedQBank.setVisibility(View.VISIBLE);
                binding.txtLabelSuggestedQBank.setVisibility(View.VISIBLE);


                mQuestionBankAdapter = new SuggestedQuestionBankAdapter(mContext, homeScreenData.getData().getPopularMcq(), this::onMcqItemClick);
                binding.revSuggestedQBank.setAdapter(mQuestionBankAdapter);

            } else {
                binding.revSuggestedQBank.setVisibility(View.GONE);
                binding.txtLabelSuggestedQBank.setVisibility(View.GONE);

            }

            if (homeScreenData.getData().getSuggestedVideo().size() > 0) {
                binding.revSuggestedVideo.setVisibility(View.VISIBLE);
                binding.txtLabelSuggestedVideo.setVisibility(View.VISIBLE);

                mMasterClassAdapter = new SuggestedMaterClassAdapter(mContext, homeScreenData.getData().getSuggestedVideo(), this);
                binding.revSuggestedVideo.setAdapter(mMasterClassAdapter);

            } else {
                binding.revSuggestedVideo.setVisibility(View.GONE);
                binding.txtLabelSuggestedVideo.setVisibility(View.GONE);
            }


            if (homeScreenData.getData().getLeaders().size() >= 1) {
                binding.textLeaderOne.setText(homeScreenData.getData().getLeaders().get(0).getName());
                binding.textPercentOne.setText(homeScreenData.getData().getLeaders().get(0).getPercentage() + " %");


                Glide.with(mContext)
                        .load(homeScreenData.getData().getLeaders().get(0).getUserProfilePic())
                        .error(R.drawable.ic_default)
                        .into(binding.imageLeaderOne);
            } else {
                binding.textLeaderOne.setVisibility(View.GONE);
                binding.textPercentOne.setVisibility(View.GONE);
                binding.textLeaderTwo.setVisibility(View.GONE);
                binding.textPercentTwo.setVisibility(View.GONE);
                binding.textLeaderThree.setVisibility(View.GONE);
                binding.textPercentThree.setVisibility(View.GONE);
            }


            if (homeScreenData.getData().getLeaders().size() >= 2) {
                binding.textLeaderTwo.setText(homeScreenData.getData().getLeaders().get(1).getName());
                binding.textPercentTwo.setText(homeScreenData.getData().getLeaders().get(1).getPercentage() + " %");

                Glide.with(mContext)
                        .load(homeScreenData.getData().getLeaders().get(1).getUserProfilePic())
                        .error(R.drawable.ic_default)
                        .into(binding.imageLeaderTwo);
            } else {
                binding.textLeaderTwo.setVisibility(View.INVISIBLE);
                binding.textPercentTwo.setVisibility(View.INVISIBLE);
            }

            //invalidate cache and restart karte

            if (homeScreenData.getData().getLeaders().size() >= 3) {
                binding.textLeaderThree.setText(homeScreenData.getData().getLeaders().get(2).getName());
                binding.textPercentThree.setText(homeScreenData.getData().getLeaders().get(2).getPercentage() + " %");

                Glide.with(mContext)
                        .load(homeScreenData.getData().getLeaders().get(2).getUserProfilePic())
                        .error(R.drawable.ic_default)
                        .into(binding.imageLeaderThree);
            } else {
                binding.textLeaderThree.setVisibility(View.INVISIBLE);
                binding.textPercentThree.setVisibility(View.INVISIBLE);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void selectedAnswerResult() {


        binding.txtQuestion.setText((Html.fromHtml(homeScreenData.getData().getMcqOfTheDay().get(0).getQuestion())));
        binding.txtOptionA.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(0).getOptionValue());
        binding.txtOptionB.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(1).getOptionValue());
        binding.txtOptionC.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(2).getOptionValue());
        binding.txtOptionD.setText(homeScreenData.getData().getMcqOfTheDay().get(0).getAnswers().get(3).getOptionValue());

        binding.txtOptionA.setClickable(false);
        binding.txtOptionA.setEnabled(false);
        binding.txtOptionB.setClickable(false);
        binding.txtOptionB.setEnabled(false);
        binding.txtOptionC.setClickable(false);
        binding.txtOptionC.setEnabled(false);
        binding.txtOptionD.setClickable(false);
        binding.txtOptionD.setEnabled(false);


        if (homeScreenData.getData().getMcqAnswerStatus().getIsAnswerForMcq().equals(homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer())) {


            if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 0) {
                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 1) {
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 2) {
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 3) {
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
            }


        } else {

            if (homeScreenData.getData().getMcqAnswerStatus().getIsAnswerForMcq() == 0) {
                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getIsAnswerForMcq() == 1) {
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getIsAnswerForMcq() == 2) {
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getIsAnswerForMcq() == 3) {
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
            }


            if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 0) {
                binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_false);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 1) {
                binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_false);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 2) {
                binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_false);
            } else if (homeScreenData.getData().getMcqAnswerStatus().getSelectedAnswer().get(0).getAnswer() == 3) {
                binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_false);
            }


        }


    }


    private void clickListener() {


        binding.txtOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mcqAnswer = 0;

                if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("1")) {
                    binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);

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

                    binding.txtOptionA.setClickable(false);
                    binding.txtOptionA.setEnabled(false);
                    binding.txtOptionB.setClickable(false);
                    binding.txtOptionB.setEnabled(false);
                    binding.txtOptionC.setClickable(false);
                    binding.txtOptionC.setEnabled(false);
                    binding.txtOptionD.setClickable(false);
                    binding.txtOptionD.setEnabled(false);


                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("2")) {
                        binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("3")) {
                        binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("4")) {
                        binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                }
                onSubmitAnswer();

            }
        });


        binding.txtOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mcqAnswer = 1;

                if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("2")) {


                    binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);

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

                    binding.txtOptionA.setClickable(false);
                    binding.txtOptionA.setEnabled(false);
                    binding.txtOptionB.setClickable(false);
                    binding.txtOptionB.setEnabled(false);
                    binding.txtOptionC.setClickable(false);
                    binding.txtOptionC.setEnabled(false);
                    binding.txtOptionD.setClickable(false);
                    binding.txtOptionD.setEnabled(false);


                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("1")) {
                        binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("3")) {
                        binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("4")) {
                        binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                }

                onSubmitAnswer();

            }
        });


        binding.txtOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mcqAnswer = 2;

                if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("3")) {


                    binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);

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

                    binding.txtOptionA.setClickable(false);
                    binding.txtOptionA.setEnabled(false);
                    binding.txtOptionB.setClickable(false);
                    binding.txtOptionB.setEnabled(false);
                    binding.txtOptionC.setClickable(false);
                    binding.txtOptionC.setEnabled(false);
                    binding.txtOptionD.setClickable(false);
                    binding.txtOptionD.setEnabled(false);


                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("2")) {
                        binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("1")) {
                        binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("4")) {
                        binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                }

                onSubmitAnswer();

            }
        });


        binding.txtOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mcqAnswer = 3;

                if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("4")) {


                    binding.linearOptionD.setBackgroundResource(R.drawable.bg_round_edittext_true);

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

                    binding.txtOptionA.setClickable(false);
                    binding.txtOptionA.setEnabled(false);
                    binding.txtOptionB.setClickable(false);
                    binding.txtOptionB.setEnabled(false);
                    binding.txtOptionC.setClickable(false);
                    binding.txtOptionC.setEnabled(false);
                    binding.txtOptionD.setClickable(false);
                    binding.txtOptionD.setEnabled(false);


                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("2")) {
                        binding.linearOptionB.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("1")) {
                        binding.linearOptionA.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                    if (homeScreenData.getData().getMcqOfTheDay().get(0).getCorrectAnswers().equals("3")) {
                        binding.linearOptionC.setBackgroundResource(R.drawable.bg_round_edittext_true);
                    }

                }

                onSubmitAnswer();
            }
        });


    }

    private void onSubmitAnswer() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(mContext).getUserResponse().getId()));
        params.put("mcq_of_the_day_question_id", new AppSharedPreference(mContext).getMcqOfTheDay());
        params.put("answer", Integer.toString(mcqAnswer));


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.submitAnswerOfTheDay(params).observe(getActivity(), new Observer<AnswerMcqOfTheDayModel>() {
            @Override
            public void onChanged(AnswerMcqOfTheDayModel answerMcqOfTheDayModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (answerMcqOfTheDayModel != null) {
                            if (answerMcqOfTheDayModel.getStatus() == 1) {

//                                navigateExplanation();
                                binding.btnExplanation.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }, 500);


            }
        });

    }

    private void navigateExplanation() {
        String jsonData = new Gson().toJson(homeScreenData.getData().getMcqOfTheDay().get(0));
        Bundle bundle = new Bundle();
        bundle.putString("Data", jsonData);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.questionExplanationBottomFragment, bundle);
    }


    @Override
    public void onTestClick(int position, HomeScreenModel.TestSeries data) {


        Bundle bundle = new Bundle();
        bundle.putString("quiz_id", Integer.toString(data.getId()));
        bundle.putInt("destination", 0);


        NavDirections directions = new NavDirections() {
            @Override
            public int getActionId() {
                return R.id.action_homeFragment_To_grandExamTest;
            }

            @NonNull
            @Override
            public Bundle getArguments() {
                return bundle;
            }
        };

        Navigation.findNavController(binding.getRoot()).navigate(directions);

    }

    @Override
    public void onMcqItemClick(int position, HomeScreenModel.PopularMcq data) {
        LoadLoginFragment(data);

    }

    @Override
    public void onMasterItemClick(int position, HomeScreenModel.SuggestedVideo data) {


        String isFreeForUsers = data.getIsFreeForUsers();
        String isVideoForPlanB = data.getIsVideoForPlanB();
        String plan_type = "";


        if (sharedPreference.getUserResponse().getPlan() != null) {
            plan_type = sharedPreference.getUserResponse().getPlan();
        }

        if (plan_type.equals("f") || plan_type.equals("a") || plan_type.equals("A")) {

            if (isFreeForUsers.equals("1")) {

                Bundle bundle = new Bundle();
                bundle.putString("video_id", Integer.toString(data.getId()));
                bundle.putString("subject_id", Integer.toString(data.getSubjectId()));
                bundle.putString("topic_name", data.getSubjectTitle());
                navController.navigate(R.id.mainTabFragment, bundle);

            } else {

                navController.navigate(R.id.action_homeFragment_to_buyNowFragment);
            }

        } else if (plan_type.equals("b") || plan_type.equals("B")) {

            if (isVideoForPlanB.equals("1")) {
                Bundle bundle = new Bundle();
                bundle.putString("video_id", Integer.toString(data.getId()));
                bundle.putString("subject_id", Integer.toString(data.getSubjectId()));
                bundle.putString("topic_name", data.getSubjectTitle());
                navController.navigate(R.id.mainTabFragment, bundle);
            } else {
                navController.navigate(R.id.buyNowFragment);
            }

        } else if (plan_type.equals("c") || plan_type.equals("C") || plan_type.equals("d") || plan_type.equals("D")) {

            Bundle bundle = new Bundle();
            bundle.putString("video_id", Integer.toString(data.getId()));
            bundle.putString("subject_id", Integer.toString(data.getSubjectId()));
            bundle.putString("topic_name", data.getSubjectTitle());
            navController.navigate(R.id.mainTabFragment, bundle);

        } else {
            navController.navigate(R.id.buyNowFragment);
        }


    }


    private void LoadLoginFragment(HomeScreenModel.PopularMcq data) {

        Bundle bundle = new Bundle();
        bundle.putString("subject_id", Integer.toString(data.getId()));
        bundle.putString("subject_name", data.getSubjectTitle());
        bundle.putInt("destination", 1);
        navController.navigate(R.id.allClassFragment, bundle);

    }


    @Override
    public void onResume() {
        super.onResume();

        DebugLog.e("OnResume Method Call");

    }


    @Override
    public void onPause() {
        super.onPause();

        DebugLog.e("OnPause Method Call");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        DebugLog.e("onDestroyView Method Call");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        DebugLog.e("onDestroy Method Call");
    }

    @Override
    public void onStop() {
        super.onStop();

        DebugLog.e("onStop Method Call");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        DebugLog.e("onDetach Method Call");
    }


    private void callDialogFragment() {


        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);


        DialogFragment dialogFragment = new SendOTPDialogFragment();
        dialogFragment.setCancelable(false);
        dialogFragment.show(ft, "dialog");


    }


    @SuppressLint("SetJavaScriptEnabled")
    private void loadLiveView(String data){

        binding.webLiveClass.setVisibility(View.VISIBLE);
        binding.webLiveClass.getSettings().setJavaScriptEnabled(true);
        binding.webLiveClass.setWebViewClient(new SwAWebClient());
        binding.webLiveClass.setBackgroundColor(Color.TRANSPARENT);
        binding.webLiveClass.setOnTouchListener(this::onTouch);
        binding.webLiveClass.loadDataWithBaseURL(null, data,  "text/html; charset=utf-8", "UTF-8", null);;

        binding.webLiveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.web_live_class){
            navController.navigate(R.id.liveClassesFragment);
        }
        return true;
    }


    private class SwAWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

}