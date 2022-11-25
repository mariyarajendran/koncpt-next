package app.technotech.koncpt.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SearchModel;
import app.technotech.koncpt.databinding.FragmentHomeSearchBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.SearchRecyclerAdapter;
import app.technotech.koncpt.ui.viewmodels.SearchViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;


public class HomeSearchFragment extends Fragment implements SearchRecyclerAdapter.OnSearchItemClick {


    private FragmentHomeSearchBinding binding;
    private SearchViewModel model;
    private SearchRecyclerAdapter adapter;
    private MainActivity mainActivity;


    List<SearchModel.Subject> subjectList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_search, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.setSearchViewModel(model);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        subjectList = new ArrayList<>();
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvSearch.setItemAnimator(new DefaultItemAnimator());


        binding.searchViewFileHash.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onCallSearchApi(newText);
                return false;
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


    private void onCallSearchApi(String newText) {

        DebugLog.e("TEXT AS => " + newText);

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.SearchHome.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("serach_text", newText);

        model.getSearchResult(params).observe(getActivity(), new Observer<SearchModel>() {
            @Override
            public void onChanged(SearchModel searchModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (searchModel != null) {

                                if (searchModel.getStatus() == 1) {
                                    subjectList = new ArrayList<>();
                                    subjectList = searchModel.getData().getSubject();
                                    loadData();

                                } else {
                                    subjectList.clear();
                                    subjectList = new ArrayList<>();
                                    adapter.notifyDataSetChanged();
                                }

                            } else {

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 300);

            }
        });


    }

    private void loadData() {

        adapter = new SearchRecyclerAdapter(getActivity(), subjectList, this);
        binding.rvSearch.setAdapter(adapter);
    }

    @Override
    public void onItemClickListener(SearchModel.Subject data, int position) {


        LoadLoginFragment(data);


    }


    private void LoadLoginFragment(SearchModel.Subject data) {

        Bundle bundle = new Bundle();
        bundle.putString("subject_id", Integer.toString(data.getId()));
        bundle.putString("subject_name", data.getSubjectTitle());
        Navigation.findNavController(binding.getRoot()).navigate(R.id.allClassFragment, bundle);

    }
}