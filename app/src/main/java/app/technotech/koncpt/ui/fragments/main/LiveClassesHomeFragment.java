package app.technotech.koncpt.ui.fragments.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SubjectListDataModel;
import app.technotech.koncpt.databinding.FragmentLiveClassesHomeBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.LiveClassRecyclerAdapter;
import app.technotech.koncpt.ui.viewmodels.LiveClassSubjectModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.GeneralUtils;

public class LiveClassesHomeFragment extends Fragment implements LiveClassRecyclerAdapter.OnItemClickListener {
    private FragmentLiveClassesHomeBinding binding;
    private LiveClassSubjectModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live_classes_home, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(LiveClassSubjectModel.class);
        binding.setSubjectViewMode(model);
        binding.rltSavedClass.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.downloadFragment));
        binding.relativeLiveClass.setOnClickListener(view -> {
            //Navigation.findNavController(binding.getRoot()).navigate(R.id.onLiveClassFragmen);
            Bundle bundle = new Bundle();
            bundle.putString("plan_id", "4");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.liveClassesLevelFragment,bundle);
        });
        binding.relativeLiveClassVideo.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("plan_id", "2");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.liveClassesLevelFragment,bundle);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        BottomNavigationView bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (bottomNavigationView.getVisibility() == View.GONE) {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void OnItemClick(int position, SubjectListDataModel.Datum data) {

    }
}