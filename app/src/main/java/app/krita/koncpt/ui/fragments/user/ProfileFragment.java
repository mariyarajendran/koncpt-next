package app.technotech.koncpt.ui.fragments.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentProfileBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.ProfileCallbacks;
import app.technotech.koncpt.utils.AppSharedPreference;


public class ProfileFragment extends Fragment {

  private FragmentProfileBinding binding;


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


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
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


        setHasOptionsMenu(true);

        binding.textCollegeName.setText(new AppSharedPreference(getActivity()).getUserResponse().getCollegeName());
        binding.textStudentEmail.setText(new AppSharedPreference(getActivity()).getUserResponse().getEmail());
        binding.textStudentName.setText(new AppSharedPreference(getActivity()).getUserResponse().getName());
        binding.textStudentPlan.setText("Activated Plan " +new AppSharedPreference(getActivity()).getUserResponse().getPlan());



        Glide.with(getActivity())
                .load(new AppSharedPreference(getActivity()).getUserResponse().getImage())
                .error(R.drawable.ic_default)
                .into(binding.circularImageView);


        String[] years = getResources().getStringArray(R.array.degree);

        if (!new AppSharedPreference(getActivity()).getUserResponse().getCurrAcadYear().equals("")){
            String collegeName = years[Integer.parseInt(new AppSharedPreference(getActivity()).getUserResponse().getCurrAcadYear())];
            binding.textYear.setText(collegeName);

        }


        String[] course = getResources().getStringArray(R.array.course);


        if (!TextUtils.isEmpty(new AppSharedPreference(getActivity()).getUserResponse().getCourse())) {
            binding.textCourse.setText(course[Integer.parseInt(new AppSharedPreference(getActivity()).getUserResponse().getCourse())]);
        }


        BottomNavigationView bottomNavigationView = ((MainActivity)requireActivity()).getBottomNavigationView();

        if (bottomNavigationView.getVisibility() == View.VISIBLE){
            bottomNavigationView.setVisibility(View.GONE);
        }



        clickListeners();

    }

    private void clickListeners() {


        binding.setProfileCallbacks(new ProfileCallbacks() {
            @Override
            public void onEditProfile() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.editProfileFragment);
            }

            @Override
            public void onBuyNow() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyNowFragment);
            }

            @Override
            public void onCourse() {

            }

            @Override
            public void onChangePassword() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.updatePasswordFragment);
            }

            @Override
            public void onChangePhoneNo() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.changePhoneFrgament);
            }
        });

    }
}