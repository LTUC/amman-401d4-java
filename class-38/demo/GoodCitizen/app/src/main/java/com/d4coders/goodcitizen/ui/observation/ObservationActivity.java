package com.d4coders.goodcitizen.ui.observation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Problem;
import com.amplifyframework.datastore.generated.model.User;
import com.d4coders.goodcitizen.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ObservationActivity extends AppCompatActivity {

    private static final String TAG = "LogActivity";

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);

        handler = new Handler(Looper.getMainLooper(), msg -> {
            reportProblem(msg.getData().getString("userId"));
            return true;
        });

        Button save = findViewById(R.id.btn_save);
        save.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
            String userId = sharedPreferences.getString("userId", "NO ID FOUND");

            reportProblem(userId);
        });

        Button camera = findViewById(R.id.camera);
        camera.setOnClickListener(view -> {
            startActivity(new Intent(ObservationActivity.this, CameraActivity.class));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 214) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String[] list = data.getStringArrayExtra("resultData");

                Log.i(TAG, "the images are 1 => " + list[0]);
                Log.i(TAG, "the images are 2 => " + list[1]);
                Log.i(TAG, "the images are 3 => " + list[2]);
            }
        }
    }

    private void reportProblem(String userId) {
        Problem problem = Problem.builder()
                        .title("Car Accident")
                        .userId(userId)
                        .description("Near Marj Al Hamman")
                        .build();

        Amplify.API.mutate(
                ModelMutation.create(problem),
                logSaved -> {
                    Log.i(TAG, "Problem reported");
                },
                error -> {
                    Log.e(TAG, "Problem not reported => " + error);
                });
    }
}