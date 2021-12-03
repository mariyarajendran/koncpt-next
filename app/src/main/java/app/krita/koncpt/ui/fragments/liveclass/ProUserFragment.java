package app.technotech.koncpt.ui.fragments.liveclass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import app.technotech.koncpt.R;

public class ProUserFragment extends Fragment {

    View view;
    public ProUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pro_users, container, false);
        AppCompatButton viewPlans=(AppCompatButton)view.findViewById(R.id.viewPlans);
        viewPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.buyNowFragment);

            }
        });
        return view;
    }

}
