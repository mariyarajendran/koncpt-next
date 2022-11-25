package app.technotech.koncpt.ui.fragments.testTabs;

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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.ResultShowModel;
import app.technotech.koncpt.databinding.FragmentResultExamBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.ShowResultCallback;
import app.technotech.koncpt.ui.viewmodels.ResultShowViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;


public class ResultExamFragment extends Fragment {

    private FragmentResultExamBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private ResultShowViewModel model;
    private Context mContext;
    private View mView;


    private ResultShowModel resultShowModelData;
    private String quiz_Id;
    private int destination;

    private BottomNavigationView bottomNavigationView;


    public ResultExamFragment() {
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
            quiz_Id = getArguments().getString("quiz_id");
            destination = getArguments().getInt("destination");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result_exam, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(ResultShowViewModel.class);
        binding.setResultViewModel(model);


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        buttonClickListener();

        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();

        if (bottomNavigationView.getVisibility() == View.VISIBLE){
            bottomNavigationView.setVisibility(View.GONE);
        }

        onApiCall();

    }

    private void buttonClickListener() {

        binding.setResultCallback(new ShowResultCallback() {
            @Override
            public void onBack() {

            }

            @Override
            public void onReviewQuestions() {
                Bundle bundle = new Bundle();
                bundle.putString("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
                bundle.putString("quiz_id", quiz_Id);
                bundle.putInt("destination", destination);

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_resultExamToReviewExamFragment, bundle);
            }
        });

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }
    private void onApiCall() {

        Map<String, String> params =  new HashMap<>();
        params.put("user_id", String.valueOf(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("quiz_id", quiz_Id);

        if (!progressDialog.isShowing()){
            progressDialog.show();
        }

        model.getResultData(params).observe(getActivity(), new Observer<ResultShowModel>() {
            @Override
            public void onChanged(ResultShowModel resultShowModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }



                        if (resultShowModel != null){
                            if (resultShowModel.getStatus() == 1){
                                resultShowModelData = resultShowModel;

                                String jsonData = new Gson().toJson(resultShowModel);

                                DebugLog.e("Json > " + jsonData );
                                DebugLog.e("Json > " + jsonData );
                                DebugLog.e("Json > " + jsonData );
                                DebugLog.e("Json > " + jsonData );
                                DebugLog.e("Json > " + jsonData );
                                DebugLog.e("Json > " + jsonData );
                                DebugLog.e("Json > " + jsonData );


                                loadData();
                            } else {
                                Toasty.error(getActivity(), resultShowModel.getMessage()).show();
                            }
                        }


                    }
                }, 500);

            }
        });

    }

    private void loadData() {

        String stringOne = resultShowModelData.getData().getQcorrectAnswerQuestions() + " Correct";
        String stringTwo = resultShowModelData.getData().getWrongAnswerQuestions() + " Incorrect";
        String stringNotAttempt = resultShowModelData.getData().getNotAnsweredQuestions() + " Not attempted";

        binding.textViewCorrect.setText(stringOne);
        binding.textViewNotCorrect.setText(stringTwo);
        binding.textViewNotAttemped.setText(stringNotAttempt);

        String score = "Your score was " + resultShowModelData.getData().getPercentage() + " %";
        binding.txtPercentage.setText(score);

        int percentage =  Math.round(Float.parseFloat(String.valueOf(resultShowModelData.getData().getPercentage())));

        if ( percentage > 0){
            binding.seek.setCurProcess(percentage);
            binding.textview.setText(percentage+"");
        } else{
            binding.seek.setCurProcess(0);
            binding.textview.setText("0");
        }

    }
}