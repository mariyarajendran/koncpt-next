package app.technotech.koncpt.ui.fragments.qbank.custommodule;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.adroitandroid.chipcloud.Chip;
import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;

import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.TagsModel;
import app.technotech.koncpt.databinding.FragmentCreateCustomModuleTwoBinding;
import app.technotech.koncpt.ui.callbacks.CustomModuleOneCallback;
import app.technotech.koncpt.ui.viewmodels.CustomModuleViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class CreateCustomModuleTwoFragment extends Fragment {


    private Context mContext;
    private FragmentCreateCustomModuleTwoBinding binding;
    private CustomModuleViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private View mView;

    private TagsModel tagsData;
    private Bundle bundle;

    ArrayList<String> tags = new ArrayList<>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            bundle = getArguments();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_custom_module_two, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(CustomModuleViewModel.class);
        binding.setCustomViewModel(model);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        initUI(view);


        binding.radioTagGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                int selected = radioGroup.getCheckedRadioButtonId();

                switch (selected) {
                    case R.id.rd_btn_all_tag:
                        selectAllItem();
                        break;

                    case R.id.rd_btn_choose_tag:
                        deSelectAllItem();
                        break;
                }

            }
        });


    }

    private void deSelectAllItem() {
        for (int i = 0; i < binding.chipTags.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipTags.getChildAt(i);
            chip.deselect();
            chip.setSelected(false);
        }
    }

    private void selectAllItem() {
        for (int i = 0; i < binding.chipTags.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipTags.getChildAt(i);
            chip.select();
            chip.setSelected(true);
        }
    }

    private void initUI(View view) {
        utils = new GeneralUtils(mContext);
        progressDialog = utils.showProgressDialog();
        buttonClickListener();

        onApiCall();
    }

    private void onApiCall() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getTags().observe(getActivity(), new Observer<TagsModel>() {
            @Override
            public void onChanged(TagsModel tagsModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                        if (tagsModel != null) {
                            if (tagsModel.getStatus() == 1) {
                                tagsData = tagsModel;
                                loadData();
                            }
                        }
                    }
                }, 500);

            }
        });


    }

    private void loadData() {

        String[] data = new String[tagsData.getData().size()];
        data = tagsData.getData().toArray(data);

        String[] finalData = data;
        new ChipCloud.Configure()
                .chipCloud(binding.chipTags)
                .selectedColor(Color.parseColor("#D9E5C3"))
                .selectedFontColor(Color.parseColor("#000000"))
                .deselectedColor(Color.parseColor("#EEEEEE"))
                .deselectedFontColor(Color.parseColor("#000000"))
                .selectTransitionMS(500)
                .deselectTransitionMS(250)
                .labels(data)
                .mode(ChipCloud.Mode.MULTI)
                .allCaps(false)
                .gravity(ChipCloud.Gravity.STAGGERED)
                .textSize(getResources().getDimensionPixelSize(R.dimen._10ssp))
                .verticalSpacing(getResources().getDimensionPixelSize(R.dimen._16sdp))
                .minHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen._32sdp))
                .chipListener(new ChipListener() {
                    @Override
                    public void chipSelected(int index) {

                        Chip chip = (Chip) binding.chipTags.getChildAt(index);
                        chip.setSelected(true);

                    }

                    @Override
                    public void chipDeselected(int index) {

                        Chip chip = (Chip) binding.chipTags.getChildAt(index);
                        chip.setSelected(false);

                    }
                })
                .build();


        binding.rdBtnAllTag.setChecked(true);

    }

    private void buttonClickListener() {


        binding.setCustomCallback(new CustomModuleOneCallback() {
            @Override
            public void onPrevious() {
                Navigation.findNavController(binding.getRoot()).popBackStack();
            }

            @Override
            public void onNext() {
//

                tags = new ArrayList<>();

                for (int i = 0; i < binding.chipTags.getChildCount(); i++) {
                    Chip chip = (Chip) binding.chipTags.getChildAt(i);
                    if (chip.isSelected()) {
                        tags.add(chip.getText().toString());
                    }
                }


                String tagsCount;

                if (tags.size() == binding.chipTags.getChildCount()){
                    tagsCount = "All";
                } else {
                    tagsCount = tags.size()+"";
                }

                if (tags.size() > 0) {
                    String tagsString = TextUtils.join(",", tags);
                    bundle.putString("tags", tagsString);
                    bundle.putString("tags_size", tagsCount);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_createCustomModuleTwoFragment_to_createCustomModuleThreeFragment, bundle);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}