package app.technotech.koncpt.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import app.technotech.koncpt.databinding.FragmentCreateCustomModuleOneBinding;
import app.technotech.koncpt.ui.adapter.custommoduleadapters.CustomModuleSubjectAdapter;
import app.technotech.koncpt.ui.viewmodels.CustomModuleViewModel;
import app.technotech.koncpt.utils.GeneralUtils;

public class CustomTopicsDialog extends DialogFragment {

    private CustomModuleSubjectAdapter mAdapter;

    private FragmentCreateCustomModuleOneBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    private CustomModuleViewModel model;
    private View mView;
    private Context mContext;

    private String subject_Id;

    private Bundle bundle;

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
}
