package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BookmarkSingleQuestionModel;
import app.technotech.koncpt.data.network.model.QuestionBookmarkModel;
import app.technotech.koncpt.databinding.FragmentBookmarkDetailBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.viewmodels.MCQsViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class BookmarkDetailFragment extends Fragment {
    private FragmentBookmarkDetailBinding binding;
    private Context mContext;
    private View mView;
    private String data;
    private String question_id;
    private MCQsViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private QuestionBookmarkModel.Datum questionItem;

    public BookmarkDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString("data");
            questionItem = new Gson().fromJson(data, new TypeToken<QuestionBookmarkModel.Datum>() {
            }.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark_detail, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(MCQsViewModel.class);
        binding.setMcqModelView(model);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
//        binding.txtQuestion.setText(Html.fromHtml(questionItem.getQuestion()));
        setHasOptionsMenu(true);
        onApiCall();
    }

    private void onApiCall() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.BookmarkSingleQuestion.getValue());
        params.put("question_id", Integer.toString(questionItem.getQuestionId()));
        params.put("level_id",new AppSharedPreference(getActivity()).getLevelId());
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getSingleAnswerBookmark(params).observe(getActivity(), new Observer<BookmarkSingleQuestionModel>() {
            @Override
            public void onChanged(BookmarkSingleQuestionModel bookmarkSingleQuestionModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        try {

                            if (bookmarkSingleQuestionModel != null) {
                                if (bookmarkSingleQuestionModel.getStatus() == 1) {

                                    loadData(bookmarkSingleQuestionModel);

                                } else {
                                    Toasty.error(getActivity(), bookmarkSingleQuestionModel.getMessage()).show();
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

    private void loadData(BookmarkSingleQuestionModel model) {

        binding.txtQuestion.setText(Html.fromHtml(model.getData().get(0).getQuestion()));
        binding.txtOptionA.setText(Html.fromHtml(model.getData().get(0).getAnswers().get(0).getOptionValue()));
        binding.txtOptionB.setText(Html.fromHtml(model.getData().get(0).getAnswers().get(1).getOptionValue()));
        binding.txtOptionC.setText(Html.fromHtml(model.getData().get(0).getAnswers().get(2).getOptionValue()));
        binding.txtOptionD.setText(Html.fromHtml(model.getData().get(0).getAnswers().get(3).getOptionValue()));


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

        }

        return super.onOptionsItemSelected(item);
    }
}