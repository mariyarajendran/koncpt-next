package app.technotech.koncpt.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import java.util.Objects;

import app.technotech.koncpt.R;
import app.technotech.koncpt.ui.fragments.user.WelcomeFragment;

public class UserAuthanticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authantication);

        loadFragment();

    }

    private void loadFragment() {

        Fragment fragment;
        fragment = new WelcomeFragment();

        if (getSupportFragmentManager().findFragmentById(R.id.container) != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.container))).commit();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }
}