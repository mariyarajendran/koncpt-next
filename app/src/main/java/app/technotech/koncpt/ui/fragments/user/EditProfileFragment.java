package app.technotech.koncpt.ui.fragments.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.databinding.FragmentEditProfileBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.callbacks.EditProfileCallbacks;
import app.technotech.koncpt.ui.viewmodels.EditUserProfileViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EditProfileFragment extends Fragment implements MainActivity.UpdateActivityResult {


    private FragmentEditProfileBinding binding;
    private EditUserProfileViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private File uploadFile = null;
    private Uri uri = null;
    String[] degreeYear;

    boolean isImageUpdate = false;

    int selectedYear;

    public EditProfileFragment() {
        // Required empty public constructor
    }


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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(EditUserProfileViewModel.class);
        binding.setEditViewModel(model);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setActivityHandler(this);
        }
        setHasOptionsMenu(true);
        init();
        clicklistener();

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    private void init() {

        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();

        binding.editName.setText(new AppSharedPreference(getActivity()).getUserResponse().getName());
        binding.editMobile.setText(new AppSharedPreference(getActivity()).getUserResponse().getPhone());
        binding.editSchoolName.setText(new AppSharedPreference(getActivity()).getUserResponse().getCollegeName());


        Glide.with(getActivity())
                .load(new AppSharedPreference(getActivity()).getUserResponse().getImage())
                .error(R.drawable.ic_default)
                .into(binding.circularImageView);
        bindYearsSpinner();

    }

    private void clicklistener() {

        binding.setEditCallbacks(new EditProfileCallbacks() {
            @Override
            public void onImageUpload() {


                ImagePicker.Companion.with(getActivity())
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(512)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(512, 512)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }

            @Override
            public void onSaveProfile() {


                if (validation()) {

                    if (isImageUpdate) {
                        uploadProfile();
                    } else {
                        UpdateData();
                    }


                }


            }
        });
    }

    private void UpdateData() {


        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("fullname", binding.editName.getText().toString());
        params.put("curr_acad_year", Integer.toString(selectedYear));
        params.put("phone", binding.editMobile.getText().toString());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getUpdateProfile2(params).observe(getActivity(), new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin userModelLogin) {


                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                        if (userModelLogin != null) {
                            if (userModelLogin.getStatus() == 1) {

                                Toasty.success(getActivity(), userModelLogin.getMessage()).show();
                                String json = new Gson().toJson(userModelLogin.getData());
                                new AppSharedPreference(getActivity()).addUserData(json);


                                Navigation.findNavController(binding.getRoot()).popBackStack();

                            }
                        }

                    }
                }, 2500);


            }
        });


    }


    private void uploadProfile() {


        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        builder.addFormDataPart("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()))
                .addFormDataPart("fullname", binding.editName.getText().toString())
                .addFormDataPart("curr_acad_year", Integer.toString(selectedYear))
                .addFormDataPart("phone", binding.editMobile.getText().toString());


        if (uploadFile.exists()) {
            builder.addFormDataPart("image", uploadFile.getName(), RequestBody.create(MultipartBody.FORM, uploadFile));
        }

        RequestBody requestBody = builder.build();


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getUpdateProfile3(requestBody).observe(getActivity(), new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin userModelLogin) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (userModelLogin != null) {
                            if (userModelLogin.getStatus() == 1) {


                                Toasty.success(getActivity(), userModelLogin.getMessage()).show();
                                String json = new Gson().toJson(userModelLogin.getData());
                                new AppSharedPreference(getActivity()).addUserData(json);

//                                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.homeFragment);

                                Navigation.findNavController(binding.getRoot()).popBackStack();


                            } else {
                                Toasty.error(getActivity(), userModelLogin.getMessage()).show();
                            }
                        }

                    }
                }, 2000);

            }
        });

    }


    private boolean validation() {

        if (TextUtils.isEmpty(binding.editName.getText())) {
            binding.editName.setError("Please enter name");
            return false;
        }


        if (TextUtils.isEmpty(binding.editMobile.getText())) {
            binding.editName.setError("Please enter mobile number");
            return false;
        }

        if (binding.editMobile.getText().toString().length() != 10) {
            binding.editMobile.setError("Please enter valid mobile number");
            return false;
        }

        if (selectedYear == 0) {
            Toasty.info(getActivity(), "please select your academic status").show();
            return false;
        }


//
//        if (uploadFile != null){
//            return false;
//        }


        return true;
    }

    @Override
    public void loadOnActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            uploadFile = ImagePicker.Companion.getFile(data);

            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(uploadFile)
                    .centerCrop()
                    .into(new BitmapImageViewTarget(binding.circularImageView) {

                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                            roundedBitmapDrawable.setCircular(true);
                            binding.circularImageView.setImageDrawable(roundedBitmapDrawable);
                        }
                    });

            isImageUpdate = true;
        }

    }

    private void bindYearsSpinner() {


        if (!new AppSharedPreference(getActivity()).getUserResponse().getCurrAcadYear().equals("")){
            selectedYear = Integer.parseInt(new AppSharedPreference(getActivity()).getUserResponse().getCurrAcadYear());
        } else {
            selectedYear = 0;
        }


        degreeYear = getActivity().getResources().getStringArray(R.array.degree);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, degreeYear);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.editStatus.setAdapter(arrayAdapter);
        binding.editStatus.setSelection(selectedYear);
        binding.editStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    selectedYear = 0;
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    selectedYear = i;
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}