package app.technotech.koncpt;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import app.technotech.koncpt.data.network.model.BookmarkSaveModel;
import app.technotech.koncpt.data.network.model.ReviewModelResponse;
import app.technotech.koncpt.databinding.FragmentQuestionExplanationBottomBinding;
import app.technotech.koncpt.ui.adapter.ReferenceBookAdapter;
import app.technotech.koncpt.ui.callbacks.ExplanationCallbacks;
import app.technotech.koncpt.ui.viewmodels.BookmarkViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;


public class QuestionExplanationBottomFragment extends BottomSheetDialogFragment {


    private FragmentQuestionExplanationBottomBinding binding;
    private BookmarkViewModel model;
    private GeneralUtils utils;
    private AlertDialog progressDialog;


    private Dialog mDialog;
    private ReviewModelResponse.Datum quesItem;
    private ReferenceBookAdapter mAdapter;

    private String webData = "<!DOCTYPE html><p>The structure marked in the above <strong>histological</strong><br /><strong>section of the lymph node</strong> are as follows</p>\n" +
            "<p><br />1. Capsule;<br />2. Subcapsular sinus;<br />3. Germinal Center;<br />4. Lymphoid Nodules;<br />5. Trabeculae;</p>\n" +
            "<p><strong>Histology of lymph node</strong></p>\n" +
            "<p><br />Lorem ipsum dolor sit amet, consectetur adipiscing<br />elit, sed do eiusmod tempor incididunt ut labore et<br />dolore magna aliqua. Ut enim ad minim veniam, quis<br />nostrud.</p>\n" +
            "<ul>\n" +
            "<li style=\"text-align: justify;\"><strong>Cortex</strong> - ullamco laboris nisi ut aliquip ex ea<br />commodo consequat. Duis aute irure dolor in<br />reprehenderit in voluptate velit esse cillum.</li>\n" +
            "</ul>\n" +
            "<p>&nbsp;</p>\n" +
            "<ul>\n" +
            "<li style=\"text-align: justify;\"><strong>Paracortex</strong> - dolore eu fugiat nulla pariatur.<br />Excepteur sint occaecat cupidatat non proident,<br />sunt in culpa</li>\n" +
            "</ul>\n" +
            "<p>&nbsp;</p>\n" +
            "<ul>\n" +
            "<li style=\"text-align: justify;\"><strong>Medulla</strong> - qui officia deserunt mollit anim id est<br />laborum</li>\n" +
            "</ul>\n" +
            "<p><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"data:image/jpeg;base64,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\" alt=\"\" width=\"121\" height=\"91\" /></p>\n" +
            "<p>&nbsp;</p>\n" +
            "<p>&nbsp;</p>\n" +
            "<ol>\n" +
            "<li>Capsule;</li>\n" +
            "<li>Subcapsular sinus;</li>\n" +
            "<li>Germinal Center;</li>\n" +
            "<li>Lymphoid Nodules;</li>\n" +
            "<li>Trabeculae;</li>\n" +
            "</ol></html>";

    public static final String TAG = "ActionBottomDialog";
    ;

    private Context mContext;
    private View mView;


    public static QuestionExplanationBottomFragment newInstance() {
        return new QuestionExplanationBottomFragment();
    }

    public QuestionExplanationBottomFragment() {
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

            DebugLog.e("Data => " + getArguments().getString("Data"));

            quesItem = new Gson().fromJson(getArguments().getString("Data"), new TypeToken<ReviewModelResponse.Datum>() {
            }.getType());
            webData = quesItem.getExplanation();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_explanation_bottom, container, false);
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
        binding.txtQuestion.setText(Html.fromHtml(quesItem.getQuestion()));
        binding.txtOptionA.setText(quesItem.getAnswers().get(0).getOptionValue());
        binding.txtOptionB.setText(quesItem.getAnswers().get(1).getOptionValue());
        binding.txtOptionC.setText(quesItem.getAnswers().get(2).getOptionValue());
        binding.txtOptionD.setText(quesItem.getAnswers().get(3).getOptionValue());


        if (!TextUtils.isEmpty(quesItem.getRefrenceFrom()) && !quesItem.getRefrenceFrom().equals("NA")) {
            binding.revReferenceBook.setVisibility(View.VISIBLE);
            binding.txtReferenceLabel.setVisibility(View.VISIBLE);
            binding.revReferenceBook.setText(quesItem.getRefrenceFrom());
        }


        if (!TextUtils.isEmpty(quesItem.getQuestionFile()) && !quesItem.getQuestionFile().equals("NA")) {

            Glide.with(getActivity())
                    .load(quesItem.getQuestionFile())
                    .error(R.drawable.app_logo)
                    .into(binding.imgQuestionImage);

            binding.imgQuestionImage.setVisibility(View.VISIBLE);

        }

        binding.txtMcqId.setText("MCQ ID : " + quesItem.getId());


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

                //     callBookMarkApi();
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
                if (!TextUtils.isEmpty(quesItem.getQuestionFile()) && !quesItem.getQuestionFile().equals("NA")) {
                    GeneralUtils.openImageDialog(getActivity(), quesItem.getQuestionFile());
                }

            }
        });

    }

    private void callBookMarkApi() {

        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.SaveBookMark.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(mContext).getUserResponse().getId()));
        params.put("item_id", Integer.toString(quesItem.getId()));


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