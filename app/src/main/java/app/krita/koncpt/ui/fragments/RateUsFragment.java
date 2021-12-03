package app.technotech.koncpt.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kobakei.ratethisapp.RateThisApp;

import org.jetbrains.annotations.NotNull;

import app.technotech.koncpt.R;

public class RateUsFragment extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_rate_us, container, false);
        // Custom condition: 3 days and 2 launches
        RateThisApp.Config config = new RateThisApp.Config(3, 2);
        RateThisApp.init(config);

        // Monitor launch times and interval from installation
        RateThisApp.onCreate(getContext());
        // If the condition is satisfied, "Rate this app" dialog will be shown
        RateThisApp.showRateDialogIfNeeded(getContext());

        RateThisApp.setCallback(new RateThisApp.Callback() {
            @Override
            public void onYesClicked() {
                Toast.makeText(getContext(), "Yes event", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoClicked() {
                Toast.makeText(getContext(), "No event", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelClicked() {
                Toast.makeText(getContext(), "Cancel event", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

}