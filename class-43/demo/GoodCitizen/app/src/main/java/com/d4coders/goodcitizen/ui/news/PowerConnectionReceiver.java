package com.d4coders.goodcitizen.ui.news;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

    private static final String TAG = "Battery";

    @Override
    public void onReceive(Context context, Intent batteryStatus) {
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        Toast.makeText(context, "usbCharge => " + usbCharge, Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "acCharge => " + acCharge, Toast.LENGTH_SHORT).show();

        Log.i(TAG, "usbCharge => " + usbCharge);
        Log.i(TAG, "acCharge => " + acCharge);
    }
}
