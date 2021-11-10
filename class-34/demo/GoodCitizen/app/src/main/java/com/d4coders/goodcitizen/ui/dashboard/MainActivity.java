package com.d4coders.goodcitizen.ui.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.observation.ObservationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcome = findViewById(R.id.welcome);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        welcome.setText(bundle.getString("userId"));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ObservationActivity.class));
        });
    }
}