package app.technotech.koncpt.ui.fragments.testTabs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.AnswerKeyValue;
import app.technotech.koncpt.data.network.model.ReviewModelResponse;
import app.technotech.koncpt.databinding.FragmentReviewQuestionBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.testadapter.ReviewQuesRecyclerAdapter;
import app.technotech.koncpt.ui.viewmodels.ReviewViewModel;
import app.technotech.koncpt.utils.GeneralUtils;


public class ReviewQuestionFragment extends Fragment implements ReviewQuesRecyclerAdapter.OnItemClickListener {


    private String userId;
    private String quiz_id;

    private FragmentReviewQuestionBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private ReviewViewModel model;
    private String data;
    private Context mContext;
    private int destination;
    List<ReviewModelResponse.Datum> questionList = new ArrayList<>();

    boolean isClicked = false;
    private long mLastClickTime = 0;

    private ReviewModelResponse reviewData;

    private Toolbar toolbar;
    private NavController navController;

    public static ReviewQuestionFragment newInstance(String param1, String param2) {
        ReviewQuestionFragment fragment = new ReviewQuestionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString("user_id");
            quiz_id = getArguments().getString("quiz_id");
            destination = getArguments().getInt("destination");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_question, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(ReviewViewModel.class);
        binding.setReviewViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        toolbar = ((MainActivity) requireActivity()).getToolbar();
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();


        if (destination == 1) {


            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mIntent = new Intent(getActivity(), MainActivity.class);
                    startActivity(mIntent);
                    requireActivity().finish();

                }
            });

        }

//

        onApiCall();

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    private void onApiCall() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("quiz_id", quiz_id);

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getReviewQuestions(params).observe(getActivity(), new Observer<ReviewModelResponse>() {
            @Override
            public void onChanged(ReviewModelResponse reviewModelResponse) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


                try {

                    if (reviewModelResponse != null) {
                        if (reviewModelResponse.getStatus() == 1) {
                            reviewData = reviewModelResponse;
                            questionList = reviewData.getData();
                            loadData();
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });


    }

    private void loadData() {

        String test = reviewData.getClickAnswer().get(0).getAnswers();
        List<AnswerKeyValue> valueList = new ArrayList<>();
        if (!test.equals("[]")) {
            String num = test.replaceAll("[\\[\\](){}]", "");
            String newString = "{" + num + "}";
            String str[] = num.split(",");

            for (int i = 0; i < str.length; i++) {
                AnswerKeyValue keyValue = new AnswerKeyValue();
                String[] parts = str[i].split(":");

                String key = parts[0].replace("\"", "");
                String value = parts[1].replace("\"", "");

                keyValue.setKey(key);
                keyValue.setValue(value);
                valueList.add(keyValue);
            }
        }


        binding.recyclerReview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerReview.setItemAnimator(new DefaultItemAnimator());

        ReviewQuesRecyclerAdapter recyclerAdapter = new ReviewQuesRecyclerAdapter(getActivity(), questionList, valueList, this);
        binding.recyclerReview.setAdapter(recyclerAdapter);

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onExplanationItem(int position) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        String jsonData = new Gson().toJson(questionList.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("Data", jsonData);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.questionExplanationBottomFragment, bundle);
    }


}