package app.technotech.koncpt.ui.fragments.qbank.custommodule;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.FragmentCustomReviewExamModelBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.custommoduleadapters.CustomReviewAdapter;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;


public class CustomReviewExamModelFragment extends Fragment implements CustomReviewAdapter.OnItemClickListener {


    private FragmentCustomReviewExamModelBinding binding;
    private List<CustomExamModel.Datum> dataList = new ArrayList<>();
    private long mLastClickTime = 0;
    private int destination;

    private Toolbar toolbar;
    private NavController navController;


    public CustomReviewExamModelFragment() {
        // Required empty public constructor
    }


    public static CustomReviewExamModelFragment newInstance(String param1, String param2) {
        CustomReviewExamModelFragment fragment = new CustomReviewExamModelFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            destination = getArguments().getInt("destination");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_review_exam_model, container, false);
        binding.setLifecycleOwner(this);
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


        String jsonData = new Gson().toJson(new AppSharedPreference(getActivity()).retrieveCustomModule().getData());
        setHasOptionsMenu(true);

        dataList = new AppSharedPreference(getActivity()).retrieveCustomModule().getData();
        binding.rvCustomReview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvCustomReview.setItemAnimator(new DefaultItemAnimator());
        CustomReviewAdapter reviewAdapter = new CustomReviewAdapter(getActivity(), dataList, this);
        reviewAdapter.setHasStableIds(true);
        binding.rvCustomReview.setAdapter(reviewAdapter);

        toolbar = ((MainActivity) requireActivity()).getToolbar();
        navController = ((MainActivity) requireActivity()).getmNavController();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (destination == 1) {
                    navController.popBackStack();
                } else {

                    new AppSharedPreference(getActivity()).saveHomeScreenData(null);

                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    getActivity().finish();
                    //navController.navigate(R.id.action_customReviewModule_to_questionBankFragment);
                }
            }
        });
    }

    @Override
    public void onExplanationItem(int position) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 3000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        String jsonData = new Gson().toJson(dataList.get(position));
        Bundle bundle = new Bundle();
        bundle.putString("Data", jsonData);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.questionExplanationBottomFragment, bundle);
    }
}