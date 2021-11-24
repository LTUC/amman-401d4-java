package com.d4coders.goodcitizen.ui.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

import com.d4coders.goodcitizen.R;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // FOR DEMONSTRATION PURPOSES PLCAE THIS CODE SOME HWERE ELSE MORE APPROPRIATE
        Intent dataIntent = getIntent();
        String text = dataIntent.getStringExtra(Intent.EXTRA_TEXT);
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

//        // FOR DEMONSTRATION PURPOSES PLCAE THIS CODE SOME HWERE ELSE MORE APPROPRIATE
//        IntentFilter batteryIntentFIlter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        Intent batteryStatus = this.registerReceiver(null, batteryIntentFIlter);
//
//        // Are we charging / charged?
//        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
//                status == BatteryManager.BATTERY_STATUS_FULL;
//
//        // How are we charging?
//        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
//        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
//        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
//
//        Toast.makeText(getApplicationContext(), "isCharging => " + isCharging, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "usbCharge => " + usbCharge, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "acCharge => " + acCharge, Toast.LENGTH_SHORT).show();
    }
}