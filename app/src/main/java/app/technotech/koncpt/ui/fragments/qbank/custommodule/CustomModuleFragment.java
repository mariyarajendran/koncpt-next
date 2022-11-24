package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.QuestionSourceModel;
import app.technotech.koncpt.databinding.FragmentCustomModuleBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.custommoduleadapters.CustomModuleQFromAdapter;
import app.technotech.koncpt.ui.callbacks.CustomModuleOneCallback;

import es.dmoral.toasty.Toasty;

public class CustomModuleFragment extends Fragment {


    private FragmentCustomModuleBinding binding;

    String[] mQuestionNoList = {"10", "20", "30", "40", "50"};
    private ArrayAdapter<String> mSpAdapter;
    private CustomModuleQFromAdapter mQFromAdapter;
    private Context mContext;
    private View mView;

    private boolean isLevel = false;
    private String quest_level = "";
    private String selectedQuestion;
    private String questionFrom = "";
    private ArrayList<String> arrPositionList = new ArrayList<>();
    private ArrayList<QuestionSourceModel> modelArrayList = new ArrayList<>();

    private String selectedLevels = "";
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_module, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        setHasOptionsMenu(true);
        init();
        buttonClickListner();


        bottomNavigationView = ((MainActivity) requireActivity()).getBottomNavigationView();

        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }


        binding.radioDifficultyLevelGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                switch (selectedId) {

                    case R.id.rd_btn_all:
                        binding.linearRadioLevels.setVisibility(View.GONE);
                        isLevel = false;
                        break;

                    case R.id.rd_btn_choose_level:
                        binding.linearRadioLevels.setVisibility(View.VISIBLE);
                        isLevel = true;
                        break;

                }

            }
        });


        binding.radioDifficultySelectionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                switch (selectedId) {

                    case R.id.rd_btn_easy:
                        quest_level = "easy";
                        break;

                    case R.id.rd_btn_medium:
                        quest_level = "medium";
                        break;

                    case R.id.rd_btn_high:
                        quest_level = "hard";
                        break;
                }

            }
        });


        binding.spQNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedQuestion = mQuestionNoList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void buttonClickListner() {


        binding.setCustomCallback(new CustomModuleOneCallback() {
            @Override
            public void onPrevious() {

            }

            @Override
            public void onNext() {

                if (!isLevel) {
                    selectedLevels = "easy,medium,hard";
                } else {
                    selectedLevels = quest_level;
                }


                arrPositionList = new ArrayList<>();
                for (int i = 0; i < modelArrayList.size(); i++) {
                    if (modelArrayList.get(i).isSelected()) {
                        questionFrom = modelArrayList.get(i).getId();
                    }
                }


                if (!TextUtils.isEmpty(questionFrom)) {

                    Bundle bundle = new Bundle();
                    bundle.putString("levels", selectedLevels);
                    bundle.putString("questions", selectedQuestion);
                    bundle.putString("questions_from", questionFrom);
                    Navigation.findNavController(mView).navigate(R.id.action_customModuleFragment_to_createCustomModuleOneFragment, bundle);


                } else {

                    Toasty.info(mContext, "Please Select Questions From").show();

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


    private void init() {
        mSpAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mQuestionNoList);
        mSpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spQNo.setAdapter(mSpAdapter);


        modelArrayList = modelArrayList();
        mQFromAdapter = new CustomModuleQFromAdapter(mContext, modelArrayList);
        binding.revQFrom.setLayoutManager(new LinearLayoutManager(mContext));
        binding.revQFrom.setAdapter(mQFromAdapter);

    }


    private ArrayList<QuestionSourceModel> modelArrayList() {

        ArrayList<QuestionSourceModel> sourceModels = new ArrayList<>();
        sourceModels.add(new QuestionSourceModel("1", "All QBank MCQs", false));
        sourceModels.add(new QuestionSourceModel("2", "Bookmarked Qbank MCQs", false));
        sourceModels.add(new QuestionSourceModel("3", "Incorrect Qbank MCQs", false));
        sourceModels.add(new QuestionSourceModel("4", "Attempted Qbank MCQs", false));
        sourceModels.add(new QuestionSourceModel("5", "Unattempted Qbank MCQs", false));

        return sourceModels;
    }

}