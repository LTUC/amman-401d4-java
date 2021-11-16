package com.d4coders.goodcitizen.ui.splash;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.data.service.PushListenerService;
import com.d4coders.goodcitizen.ui.auth.AuthActivity;
import com.d4coders.goodcitizen.ui.dashboard.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        createNotificationChannel();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            //This method will be executed once the timer is over
            // Start your app main activity
            startActivity(new Intent(SplashActivity.this, AuthActivity.class));
            // close this activity
            finish();
        }, 3000); // pause the launch of the dashboard for 3 seconds
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(PushListenerService.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}