package app.technotech.koncpt.ui.adapter;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.DeleteBookmarkModel;
import app.technotech.koncpt.data.network.model.QuestionBookmarkModel;
import app.technotech.koncpt.databinding.FragmentBookmarkQuestionsBinding;
import app.technotech.koncpt.ui.viewmodels.BookmarkViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;


public class BookmarkQuestionsFragment extends Fragment implements QuestionsBookmarkRecyclerAdapter.OnItemClickListener {

    private FragmentBookmarkQuestionsBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private View mView;
    private Context mContext;
    private BookmarkViewModel model;

    private QuestionsBookmarkRecyclerAdapter adapter;
    private String bookmark_id;
    private String subject_id;
    private String user_id;
    private int position;
    private int type;
    private int destination;


    private QuestionBookmarkModel modelData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            if (getArguments().getInt("type") == 0) {
                type = getArguments().getInt("type");
                user_id = getArguments().getString("user_id");
                destination = getArguments().getInt("destination");
            } else {
                type = getArguments().getInt("type");
                user_id = getArguments().getString("user_id");
                subject_id = getArguments().getString("subject_id");
                destination = getArguments().getInt("destination");
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark_questions, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(BookmarkViewModel.class);
        binding.setQuestionViewModel(model);

        return binding.getRoot();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        setHasOptionsMenu(true);
        utils = new GeneralUtils(mContext);
        progressDialog = utils.showProgressDialog();

        if (type == 0) {
            onAllDataApi();
        } else {
            onCallApi();
        }

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    private void onCallApi() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("subject_id", subject_id);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getBookmarkedQuestions(params).observe((AppCompatActivity) mContext, new Observer<QuestionBookmarkModel>() {
            @Override
            public void onChanged(QuestionBookmarkModel questionBookmarkModel) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


                try {

                    if (questionBookmarkModel != null) {
                        if (questionBookmarkModel.getStatus() == 1) {
                            modelData = questionBookmarkModel;
                            loadData();
                        } else {
                            Toasty.error(mContext, "Failure").show();
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

    }


    private void onAllDataApi() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getAllBookmarks(params).observe((AppCompatActivity) mContext, new Observer<QuestionBookmarkModel>() {
            @Override
            public void onChanged(QuestionBookmarkModel questionBookmarkModel) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


                try {

                    if (questionBookmarkModel != null) {
                        if (questionBookmarkModel.getStatus() == 1) {
                            modelData = questionBookmarkModel;
                            loadData();
                        } else {
                            Toasty.error(mContext, "Failure").show();
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

    }


    private void loadData() {

        binding.recyclerQuestion.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerQuestion.setItemAnimator(new DefaultItemAnimator());
        adapter = new QuestionsBookmarkRecyclerAdapter(getActivity(), modelData.getData(), this);
        binding.recyclerQuestion.setAdapter(adapter);


    }


    @Override
    public void onItemClick(QuestionBookmarkModel.Datum datum, int position) {

        if (destination == 0){
            String jsonData = new Gson().toJson(modelData.getData().get(position));
            Bundle bundle = new Bundle();
            bundle.putString("data", jsonData);
            bundle.putInt("destination", 0);
            bundle.putString("question_id", datum.getQuestionId()+"");
            Navigation.findNavController(mView).navigate(R.id.action_bookmarkQuestionFragment_to_bookmarkDetailFragment, bundle);


        } else {
            String jsonData = new Gson().toJson(modelData.getData().get(position));
            Bundle bundle = new Bundle();
            bundle.putString("data", jsonData);
            bundle.putInt("destination", 1);
            bundle.putString("question_id", datum.getQuestionId()+"");
            Navigation.findNavController(mView).navigate(R.id.action_bookmarkTopicFragment_to_bookmarkDetailFragment, bundle);
        }



    }

    @Override
    public void onItemDeleteBookmark(int position) {
//        adapter.removeItem(position);

        this.position = position;
        bookmark_id = Integer.toString(modelData.getData().get(position).getBookmarkId());
        onDeleteBookmark();
    }

    private void onDeleteBookmark() {

        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("bookmark_id", bookmark_id);


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.deleteBookmark(params).observe((AppCompatActivity) mContext, new Observer<DeleteBookmarkModel>() {
            @Override
            public void onChanged(DeleteBookmarkModel deleteBookmarkModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        try {

                            if (deleteBookmarkModel != null) {
                                if (deleteBookmarkModel.getStatus() == 1) {
                                    adapter.removeItem(position);
                                }
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 300);

            }
        });


    }
}