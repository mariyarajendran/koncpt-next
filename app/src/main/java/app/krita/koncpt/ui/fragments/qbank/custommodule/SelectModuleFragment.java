package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.FragmentSelectModuleBinding;
import app.technotech.koncpt.utils.DebugLog;

public class SelectModuleFragment extends Fragment {



    private FragmentSelectModuleBinding binding;
    private String jsonData;
    private String subject_list;
    private String mode;

    private String parameter_1;
    private String parameter_2;
    private String parameter_3;
    private String parameter_4;
    private String parameter_5;
    private String parameter_6;
    private String parameter_7;
    private String parameter_8;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            jsonData = getArguments().getString("data");
            DebugLog.e(" json Data => " + jsonData);
            subject_list = getArguments().getString("subject_list");
            mode = getArguments().getString("mode");
            parameter_1 = getArguments().getString("levels", "");
            parameter_2 = getArguments().getString("questions", "");
            parameter_3 = getArguments().getString("questions_from", "");
            parameter_4 = getArguments().getString("subject_list", "");
            parameter_5 = getArguments().getString("tags", "");
            parameter_6 = getArguments().getString("subject_name_list", "");
            parameter_7 = getArguments().getString("subject_count", "");
            parameter_8 = getArguments().getString("tags_size", "");


            DebugLog.e("PARAMS =>  " + parameter_5);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_module, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);



        binding.txtSubjectVal.setText(parameter_7 + " Subjects Selected");
        binding.txtTagVal.setText(parameter_8 + " Tags Selected");

        if (parameter_1.equals("easy,medium,hard")){
            binding.txtDifficultyVal.setText("All");
        } else {
            binding.txtDifficultyVal.setText(parameter_1);
        }

        binding.txtExamModeValue.setText(mode);

        binding.textExamQuestions.setText(parameter_2 + " Questions");

        binding.textExamSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getArguments()!= null){
                    Bundle bundle = new Bundle();
                    bundle.putString("data", getArguments().getString("data", ""));
                    bundle.putString("subject_list", subject_list);
                    bundle.putString("levels", parameter_1);
                    bundle.putString("questions", parameter_2);
                    bundle.putString("questions_from", parameter_3);
                    bundle.putString("subject_list", parameter_4);
                    bundle.putString("tags", parameter_5);
                    bundle.putString("mode", mode);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_customModuleFragment_to_customExamModuleFragment, bundle);

                }


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
}