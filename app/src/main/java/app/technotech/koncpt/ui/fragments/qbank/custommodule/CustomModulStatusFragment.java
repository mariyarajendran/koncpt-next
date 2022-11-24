package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import org.json.JSONObject;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.FragmentCustomModulStatusBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.utils.AppSharedPreference;


public class CustomModulStatusFragment extends Fragment {


    private FragmentCustomModulStatusBinding binding;
    private CustomExamModel customExamModel;

    private NavController navController;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_modul_status, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        customExamModel = new AppSharedPreference(getActivity()).retrieveCustomModule();

        navController = ((MainActivity) requireActivity()).getmNavController();

        try {

            String jsonData = customExamModel.getExtraData();

            JSONObject object = new JSONObject(jsonData);
            binding.txtSubjectVal.setText(object.getString("subject_count") + " Subjects Selected");
            binding.txtTagVal.setText(object.getString("tags_size") + " Tags Selected");
            binding.txtDifficultyVal.setText(object.getString("levels"));
            binding.txtExamModeValue.setText(object.getString("mode"));
            binding.btnModuleReview.setText(object.getString("questions") + " Review Questions");

            binding.textDeleteAndCreateModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AppSharedPreference(getActivity()).saveCustomModule(null);
                    navController.popBackStack();

                }
            });


            binding.btnModuleReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putInt("destination", 1);

                    navController.navigate(R.id.action_customModuleStatus_to_customReviewModule);
                }
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}