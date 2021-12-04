package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.RatingResponse;
import app.technotech.koncpt.databinding.LayoutRattingDialogBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.viewmodels.MCQsViewModel;
import app.technotech.koncpt.utils.GeneralUtils;

public class RatingDialogFragment extends DialogFragment {

    private LayoutRattingDialogBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private MCQsViewModel model;

    private String params1;
    private String params2;
    private int rating;
    private int destination;


    public static RatingDialogFragment getParams(String params1, String params2) {
        RatingDialogFragment fragment = new RatingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("topic_id", params1);
        bundle.putString("user_id", params2);
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            params1 = bundle.getString("topic_id");
            params2 = bundle.getString("user_id");
            destination = bundle.getInt("destination");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_ratting_dialog, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(getActivity()).get(MCQsViewModel.class);
        binding.setRaingViewModel(model);


        return binding.getRoot();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.WideDialog);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rating > 0) {
                    callRatingApi();
                } else {
                    Toast.makeText(getActivity(), "Please select at least one star", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.imgCloseRattingDialog.setVisibility(View.GONE);

        binding.imgCloseRattingDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dismiss();


            }
        });

        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                rating = (int) v;

            }
        });

    }

    private void callRatingApi() {

        Map<String, String> params = new HashMap<>();
        params.put("topic_id", params1);
        params.put("user_id", params2);
        params.put("ratting", String.valueOf(rating));


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.submitrating(params).observe(getActivity(), new Observer<RatingResponse>() {
            @Override
            public void onChanged(RatingResponse ratingResponse) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        if (ratingResponse != null) {
                            if (ratingResponse.getStatus() == 1) {

                                if (destination == 0) {
                                    dismiss();

                                    NavController navController = ((MainActivity) requireActivity()).getmNavController();
                                    navController.navigate(R.id.action_ratingDialogFragment_to_questionBankFragment);

                                } else if (destination == 1) {
                                    dismiss();
                                    NavController navController = ((MainActivity) requireActivity()).getmNavController();
                                    navController.navigate(R.id.action_ratingDialogFragment_to_homeFragment);

                                }

                            } else {
                                Toast.makeText(getActivity(), "" + ratingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, 500);

            }
        });

    }


}
