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
import androidx.navigation.Navigation;

import app.technotech.koncpt.R;
import app.technotech.koncpt.databinding.PopupProBinding;

public class CustomBuyNowDialogFragment extends DialogFragment {

    private PopupProBinding binding;
    private OnNavigateToScreen onNavigateToScreen;


    public CustomBuyNowDialogFragment(OnNavigateToScreen onNavigateToScreen){
        this.onNavigateToScreen = onNavigateToScreen;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.popup_pro, container, false);
        binding.setLifecycleOwner(this);

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
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        binding.btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyNowFragment);
                dismiss();
                onNavigateToScreen.onItemNavigation();
            }
        });

    }


    public interface OnNavigateToScreen{
        public void onItemNavigation();
    }

}
