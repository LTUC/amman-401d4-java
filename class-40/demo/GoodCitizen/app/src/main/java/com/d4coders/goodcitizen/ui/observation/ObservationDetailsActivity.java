package com.d4coders.goodcitizen.ui.observation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.d4coders.goodcitizen.R;

public class ObservationDetailsActivity extends AppCompatActivity {

    public static final String OBSERVATION_ID = "observationId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_details);

        Intent intent = getIntent();
        String observationId = intent.getStringExtra(OBSERVATION_ID);
        fetchSingleObservation(observationId);
    }
    
    private void fetchSingleObservation(String observationId) {
        // TODO: 11/18/21 query dynamodb for the observation
        Toast.makeText(getApplicationContext(), "It works => " + observationId, Toast.LENGTH_SHORT).show();

    }
}