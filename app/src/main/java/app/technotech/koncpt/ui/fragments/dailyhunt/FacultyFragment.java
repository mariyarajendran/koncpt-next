package app.technotech.koncpt.ui.fragments.dailyhunt;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.FacultyModel;
import app.technotech.koncpt.databinding.FragmentFacultyBinding;
import app.technotech.koncpt.ui.adapter.FragmentAdapter;
import app.technotech.koncpt.ui.viewmodels.FacultyViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class FacultyFragment extends Fragment {


    FragmentFacultyBinding binding;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private FacultyViewModel model;
    private FragmentAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_faculty, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(FacultyViewModel.class);
        binding.setFacultyViewModel(model);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        getFacultyData();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    private void getFacultyData() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getFacultyData().observe(getActivity(), new Observer<FacultyModel>() {
            @Override
            public void onChanged(FacultyModel dailyHuntModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (dailyHuntModel != null) {
                                String jsonData = new Gson().toJson(dailyHuntModel);
                                DebugLog.e("ResponseFaculty =>>" + jsonData);
                                adapter=new FragmentAdapter(getContext(),R.layout.faculty_item,dailyHuntModel.getData());
                                binding.gridViewFaculty.setAdapter(adapter);

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });

    }

}