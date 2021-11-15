package com.d4coders.goodcitizen.ui.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.amplifyframework.core.Amplify;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.observation.ObservationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.welcome);

        userSession();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ObservationActivity.class));
        });
    }

    private void userSession() {
        Amplify.Auth.fetchUserAttributes(
                attributes -> Log.i(TAG, "User attributes = " + attributes.toString()),
                error -> Log.e(TAG, "Failed to fetch user attributes.", error)
        );

                Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i(TAG, result.toString());
                    welcome.setText(result.toString());
                },
                error -> Log.e(TAG, error.toString())
        );
    }
}