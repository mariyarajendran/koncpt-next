package app.technotech.koncpt.ui.fragments.qbank.subjectdetauktabs;

import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.QuestionDetailsEntity;
import app.technotech.koncpt.data.network.model.MCQQuestionResponse;
import app.technotech.koncpt.data.network.model.SubjectModel;
import app.technotech.koncpt.data.network.model.UserBookmarkTopicModel;
import app.technotech.koncpt.databinding.LayoutScreen23Binding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.MCQsCallbacks;
import app.technotech.koncpt.ui.viewmodels.MCQsViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class MCQsFragments extends Fragment {


    private LayoutScreen23Binding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private MCQsViewModel model;
    private MCQQuestionResponse mCQSData = new MCQQuestionResponse();
    private View mView;


    private SubjectModel.ModuleDatum data;
    private String topicId;
    private String subjectId;
    private int totalBookmark;
    private int destination;

    private BottomNavigationView bottomNavigationView;

    ArrayList<QuestionDetailsEntity> questionEntities = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            data = bundle.getParcelable("data");
            topicId = bundle.getString("topic_id");
            destination = bundle.getInt("destination");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.layout_screen_23, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(MCQsViewModel.class);
        binding.setBookmarkViewModel(model);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        loadData();
        buttonClickListener();

        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        onApiCall();

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    private void onApiCall() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("topic_id", topicId);

        DebugLog.e("Topic : " + Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()) + " == " + topicId);

        if (!progressDialog.isShowing()){
            progressDialog.show();
        }


        model.getBookmarkTopicData(params).observe(getActivity(), new Observer<UserBookmarkTopicModel>() {
            @Override
            public void onChanged(UserBookmarkTopicModel userBookmarkTopicModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }

                            if (userBookmarkTopicModel!= null){
                                if (userBookmarkTopicModel.getStatus() == 1){

                                    binding.txtTotalBookmark.setText(userBookmarkTopicModel.getData().get(0).getTotalBookmark() + " Bookmarks");
                                    subjectId = String.valueOf(userBookmarkTopicModel.getData().get(0).getSubjectId());
                                    totalBookmark = userBookmarkTopicModel.getData().get(0).getTotalBookmark();
                                }
                            }

                        } catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });


    }

    private void buttonClickListener() {

        binding.setMcqCallbacks(new MCQsCallbacks() {
            @Override
            public void onStartMCQs() {
                LoadLoginFragment();
            }

            @Override
            public void onBookmarks() {

                if (totalBookmark != 0){

                    Bundle bundle = new Bundle();
                    bundle.putString("subject_id", subjectId);
                    bundle.putString("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
                    bundle.putInt("destination", 1);
                    bundle.putInt("type", 1);

                    Navigation.findNavController(mView).navigate(R.id.action_mcqsFragment_to_bookmarkTopicFragment, bundle);


                }


            }
        });

    }


    private void loadData() {
        binding.txtTopicName.setText(data.getTopicName());
        String mcqQuestion = data.getTotalMcq() + " MCQs";
        binding.txtTotalMcq.setText(mcqQuestion);
        questionEntities = new ArrayList<>();
    }


    private void LoadLoginFragment() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", data);
        bundle.putInt("destination", destination);
        Navigation.findNavController(mView).navigate(R.id.mcqTestFragment, bundle);
    }


}
