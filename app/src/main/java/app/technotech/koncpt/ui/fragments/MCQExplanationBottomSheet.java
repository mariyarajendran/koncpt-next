package app.technotech.koncpt.ui.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.local.entites.QuestionDetailsEntity;
import app.technotech.koncpt.data.network.model.BookmarkSaveModel;
import app.technotech.koncpt.data.network.model.CustomExamModel;
import app.technotech.koncpt.databinding.BottomSheetCustomExaplanationBinding;
import app.technotech.koncpt.ui.callbacks.ExplanationCallbacks;
import app.technotech.koncpt.ui.viewmodels.BookmarkViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class MCQExplanationBottomSheet extends BottomSheetDialogFragment {

    private BottomSheetCustomExaplanationBinding binding;
    private BookmarkViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private QuestionDetailsEntity quesItem;

    private String webData;
    private Dialog mDialog;
    private Context mContext;
    private View mView;


    public MCQExplanationBottomSheet() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);

        if (getArguments() != null) {
            quesItem = new Gson().fromJson(getArguments().getString("Data"), new TypeToken<QuestionDetailsEntity>() {
            }.getType());
            webData = quesItem.getmExplanation();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_custom_exaplanation, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(BookmarkViewModel.class);
        binding.setExplainViewModel(model);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mView = view;


        utils = new GeneralUtils(mContext);
        progressDialog = utils.showProgressDialog();

        binding.webViewAnswer.loadData(webData, "text/html", "UTF-8");
        binding.webViewAnswer.setVisibility(View.VISIBLE);
        binding.txtQuestion.setText(Html.fromHtml(quesItem.getQuestion()));
        binding.txtOptionA.setText(quesItem.getmOptionA());
        binding.txtOptionB.setText(quesItem.getOptionB());
        binding.txtOptionC.setText(quesItem.getOptionC());
        binding.txtOptionD.setText(quesItem.getOptionD());


        binding.setExplainCallback(new ExplanationCallbacks() {
            @Override
            public void onCloseDialog() {
                mDialog.dismiss();
            }

            @Override
            public void onReportError() {
                Toasty.error(mContext, "Report Error").show();
            }

            @Override
            public void onBookmark() {
//                BookMarkDialogFragment.newInstance().show(getChildFragmentManager(),
//                        BookMarkDialogFragment.TAG);

                callBookMarkApi();
            }

            @Override
            public void onShare() {
                Toasty.info(mContext, "Share Mcq").show();
            }

            @Override
            public void onShareMSQ() {
                Toasty.info(mContext, "Share Mcq").show();
            }

            @Override
            public void onContinue() {
                mDialog.dismiss();
            }

            @Override
            public void onQuestionMSQs() {
                if (quesItem.getRefrence_file() != null && quesItem.getRefrence_file().contains("NA")) {
                    GeneralUtils.openImageDialog(mContext, "imagePath");
                }

            }
        });

    }

    private void callBookMarkApi() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.SaveBookMark.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(mContext).getUserResponse().getId()));
        params.put("item_id", Long.toString(quesItem.getQuestionId()));

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.addItemBookmark(params).observe(getActivity(), new Observer<BookmarkSaveModel>() {
            @Override
            public void onChanged(BookmarkSaveModel bookmarkSaveModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                try {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (bookmarkSaveModel != null) {
                                if (bookmarkSaveModel.getStatus() == 1) {
                                    Toasty.success(mContext, bookmarkSaveModel.getMessage()).show();
                                } else {
                                    Toasty.error(mContext, bookmarkSaveModel.getMessage()).show();
                                }
                            }

                        }
                    }, 300);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        this.mDialog = dialog;

        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow())
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
