package com.d4coders.goodcitizen.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.dashboard.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            //This method will be executed once the timer is over
            // Start your app main activity
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            // close this activity
            finish();
        }, 3000); // pause the launch of the dashboard for 3 seconds
    }
}