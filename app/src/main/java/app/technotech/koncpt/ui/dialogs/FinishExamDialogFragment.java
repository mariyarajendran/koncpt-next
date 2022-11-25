package app.technotech.koncpt.ui.dialogs;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.DialogFinishExamBinding;

public class FinishExamDialogFragment extends DialogFragment {

    private DialogFinishExamBinding binding;
    private onDialogFinish listener;
    private String notAttempt;



    public void setListener(onDialogFinish listener){
        this.listener = listener;
    }



    public FinishExamDialogFragment(onDialogFinish listener, String notAttempt){
        this.listener = listener;
        this.notAttempt = notAttempt;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_finish_exam, container, false);
        binding.setLifecycleOwner(getActivity());

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

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.dialogStringCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.buttonSubmitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCompleteExam();
                dismiss();
            }
        });

        String msg = "you have " +  notAttempt +" unattempted questions";
        binding.dialogStringUnattempted.setText(msg);

    }

    public interface onDialogFinish{
        void onCompleteExam();
    }

}
