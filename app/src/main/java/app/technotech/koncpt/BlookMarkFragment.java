package app.technotech.koncpt;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.data.network.model.SubjectBookmarkModel;
import app.technotech.koncpt.databinding.FragmentBlookMarkBinding;
import app.technotech.koncpt.ui.adapter.BookmarkRecyclerAdapter;
import app.technotech.koncpt.ui.viewmodels.BookmarkViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class BlookMarkFragment extends Fragment implements BookmarkRecyclerAdapter.OnItemClickListener {


    public FragmentBlookMarkBinding binding;
    public GeneralUtils utils;
    public AlertDialog progressDialog;
    public BookmarkViewModel model;
    public Context mContext;
    public View mView;

    private int totalItem;
    private List<SubjectBookmarkModel.Datum> bookmarkList = new ArrayList<>();
    private BookmarkRecyclerAdapter recyclerAdapter;

    public BlookMarkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blook_mark, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(BookmarkViewModel.class);
        binding.setBookmarkViewModel(model);
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
        setHasOptionsMenu(true);
        mView = view;
        utils = new GeneralUtils(mContext);
        progressDialog = utils.showProgressDialog();

        onCallApi();
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
        params.put(EnumApiAction.action.getValue(), EnumApiAction.SubjectBookmarks.getValue());
        params.put("level_id", new AppSharedPreference(getActivity()).getLevelId());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getSubjectData(params).observe((AppCompatActivity) mContext, new Observer<SubjectBookmarkModel>() {
            @Override
            public void onChanged(SubjectBookmarkModel subjectBookmarkModel) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                if (subjectBookmarkModel != null) {
                    if (subjectBookmarkModel.getStatus() == 1) {

                        String jsonData = new Gson().toJson(subjectBookmarkModel);
                        Log.e("Data = > ", jsonData);


                        totalItem = subjectBookmarkModel.getTotalBookmark();
                        bookmarkList = subjectBookmarkModel.getData();
                        loadData();
                    } else {
                        Toasty.success(mContext, subjectBookmarkModel.getMessage() + "Not get Data").show();
                    }
                }

            }
        });

    }

    private void loadData() {

        bookmarkList.add(0, new SubjectBookmarkModel.Datum(0, 0, "All", new AppSharedPreference(getActivity()).getUserResponse().getId(), totalItem));

        binding.recyclerBookmark.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerBookmark.setItemAnimator(new DefaultItemAnimator());
        recyclerAdapter = new BookmarkRecyclerAdapter(getActivity(), bookmarkList, this);
        binding.recyclerBookmark.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(SubjectBookmarkModel.Datum datum, int position) {


        if (position != 0) {

            Bundle bundle = new Bundle();
            bundle.putString("subject_id", Integer.toString(datum.getSubjectId()));
            bundle.putString("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
            bundle.putInt("destination", 0);
            bundle.putInt("type", 1);

            Navigation.findNavController(mView).navigate(R.id.action_blookMarkFragment_to_bookmarkQuestionFragment, bundle);

        } else {


            Bundle bundle = new Bundle();
            bundle.putString("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
            bundle.putInt("destination", 0);
            bundle.putInt("type", 0);


            Navigation.findNavController(mView).navigate(R.id.action_blookMarkFragment_to_bookmarkQuestionFragment, bundle);


        }


    }
}