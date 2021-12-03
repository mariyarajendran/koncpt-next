package app.technotech.koncpt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import app.technotech.koncpt.databinding.FragmentClassesDetailsBinding;


public class ClassesDetailsFragment extends Fragment {

//    @BindView(R.id.tabLayout)
//    TabLayout mTabLayout;

    private FragmentClassesDetailsBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_classes_details, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Class"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Slides"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Notes"));
    }
}