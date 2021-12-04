package app.technotech.koncpt.ui.fragments.main;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.QBankModel;
import app.technotech.koncpt.data.network.model.QuestionBank;
import app.technotech.koncpt.data.network.model.QuestionBankGroup;
import app.technotech.koncpt.databinding.FragmentQuestionBankBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.QBankAdapter;
import app.technotech.koncpt.ui.callbacks.QuestionsBank;
import app.technotech.koncpt.ui.viewmodels.QuestionsBankViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class QuestionBankFragment extends Fragment {

    private FragmentQuestionBankBinding binding;
    private QuestionsBankViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;

    private QBankAdapter mAdapter;
    List<QuestionBankGroup> bankGroups = new ArrayList<>();
    private Context mContext;
    private BottomNavigationView navigationView;
    private MenuItem menuItem;
    private String levelId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            levelId = getArguments().getString("level_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_bank, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(QuestionsBankViewModel.class);
        binding.setQuesViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setButtonClickListner();
        setHasOptionsMenu(true);
        navigationView = ((MainActivity) requireActivity()).getBottomNavigationView();
        if (navigationView.getVisibility() == View.GONE) {
            navigationView.setVisibility(View.VISIBLE);
        }
    }

    private void setButtonClickListner() {

        binding.setQuesCallback(new QuestionsBank() {
            @Override
            public void onClickOne() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_questionBankFragment_to_blookMarkFragment);
            }

            @Override
            public void onClickTwo() {

                //   new AppSharedPreference(getActivity()).saveCustomModule("null");

                if (!new AppSharedPreference(getActivity()).isCustomModuleGenerated()) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_questionBankFragment_to_customModuleFragment);
                } else {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_questionBankFragment_to_customModuleStatus);
                }


            }

            @Override
            public void onClickThree() {

            }
        });

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menuItem = menu.findItem(R.id.action_search);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Navigation.findNavController(binding.getRoot()).navigate(R.id.searchHomeFragment);
                return false;
            }
        });
        super.onPrepareOptionsMenu(menu);

    }

    private void init() {

        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();
        callSubjectApi();
    }

    private void callSubjectApi() {
        String userId = new AppSharedPreference(getActivity()).getUserResponse().getId().toString();
        String yearOfStudents = new AppSharedPreference(getActivity()).getUserResponse().getCurrAcadYear();
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.AllSubject.getValue());
        params.put("user_id", userId);
        params.put("level_id", levelId);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getQBank(params).observe(getActivity(), new Observer<QBankModel>() {
            @Override
            public void onChanged(QBankModel qBankModel) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (qBankModel != null) {
                                if (qBankModel.getStatus() == 1) {
                                    String bookmarkCount = qBankModel.getTotaBookmarks() + " Bookmarks";
                                    binding.txtBookMarkCount.setText(bookmarkCount);
                                    String json = new Gson().toJson(qBankModel);
                                    DebugLog.e("JSON RESPONSE => " + json);
                                    JSONObject object = new JSONObject(json);
                                    Iterator<String> keyCount = object.keys();
                                    bankGroups = new ArrayList<>();
                                    ArrayList<QuestionBank> listData = new ArrayList<>();
                                    int counter = -1;
                                    while (keyCount.hasNext()) {
                                        String keyname = keyCount.next();
                                        try {
                                            if (!keyname.equals("message") && !keyname.equals("status")) {
                                                String data = object.getString(keyname);
                                                listData = new Gson().fromJson(data, new TypeToken<List<QuestionBank>>() {
                                                }.getType());
                                                counter++;
                                                String year_name = "";
                                                switch (keyname) {
                                                    case "first_year_subject":
                                                        year_name = "First Year";
                                                        break;
                                                    case "second_year_subject":
                                                        year_name = "Second Year";
                                                        break;
                                                    case "third_year_subject":
                                                        year_name = "Third Year";
                                                        break;
                                                    case "fourth_year_subject":
                                                        year_name = "Fourth Year";
                                                        break;
                                                }
                                                QuestionBankGroup bankGroup = new QuestionBankGroup(year_name, listData);
                                                bankGroup.order = counter;
                                                bankGroups.add(bankGroup);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    for (int i = 0; i < bankGroups.size(); i++) {
                                        String name = bankGroups.get(i).getTitle();
                                        if (name.trim().equals("Fourth Year")) {
                                            List<QuestionBank> questionBank = bankGroups.get(i).getItems();
                                            bankGroups.remove(i);
                                            bankGroups.add(new QuestionBankGroup(name, questionBank));
                                        }
                                    }
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    loadData();
                                } else {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 500);
            }
        });
    }

    private void loadData() {
        RecyclerView.ItemAnimator animator = binding.revQBank.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        AppCompatActivity activity = (AppCompatActivity) getContext();
        mAdapter = new QBankAdapter(activity, bankGroups);
        binding.revQBank.setLayoutManager(new LinearLayoutManager(mContext));
        binding.revQBank.setAdapter(mAdapter);
        mAdapter.expandAllGroups();
    }
}