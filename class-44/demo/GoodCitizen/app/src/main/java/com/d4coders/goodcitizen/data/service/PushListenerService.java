package com.d4coders.goodcitizen.data.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.dashboard.DashboardActivity;
import com.d4coders.goodcitizen.ui.news.NewsActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

public class PushListenerService extends FirebaseMessagingService {
    public static final String TAG = PushListenerService.class.getSimpleName();

    public static final String CHANNEL_ID = "CHANNEL_ID";

    // Intent action used in local broadcast
    public static final String ACTION_PUSH_NOTIFICATION = "push-notification";
    // Intent keys
    public static final String INTENT_SNS_NOTIFICATION_FROM = "from";
    public static final String INTENT_SNS_NOTIFICATION_DATA = "data";
    public static final int NOTIFICATION_ID = 999;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        Log.d(TAG, "Registering push notifications token: " + token);
        DashboardActivity.getPinpointManager(getApplicationContext()).getNotificationClient().registerDeviceToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Message: " + remoteMessage.getData());

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NewsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // this code works from google notification docs
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(remoteMessage.getData().get("pinpoint.notification.title"))
                .setSmallIcon(R.drawable.ic_arrow_left)
                .setContentText(remoteMessage.getData().get("pinpoint.notification.body"))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Log.i(TAG, remoteMessage.getData().toString());

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());

//        final NotificationClient notificationClient = MainActivity.getPinpointManager(getApplicationContext()).getNotificationClient();
//
//        final NotificationDetails notificationDetails = NotificationDetails.builder()
//                .from(remoteMessage.getFrom())
//                .mapData(remoteMessage.getData())
//                .intentAction(NotificationClient.FCM_INTENT_ACTION)
//                .build();
//
//        NotificationClient.CampaignPushResult pushResult = notificationClient.handleCampaignPush(notificationDetails);
//
//        if (!NotificationClient.CampaignPushResult.NOT_HANDLED.equals(pushResult)) {
//            /**
//             The push message was due to a Pinpoint campaign.
//             If the app was in the background, a local notification was added
//             in the notification center. If the app was in the foreground, an
//             event was recorded indicating the app was in the foreground,
//             for the demo, we will broadcast the notification to let the main
//             activity display it in a dialog.
//             */
//            if (NotificationClient.CampaignPushResult.APP_IN_FOREGROUND.equals(pushResult)) {
//                /* Create a message that will display the raw data of the campaign push in a dialog. */
//                final HashMap<String, String> dataMap = new HashMap<>(remoteMessage.getData());
//                broadcast(remoteMessage.getFrom(), dataMap);
//            }
//            return;
//        }
    }

    private void broadcast(final String from, final HashMap<String, String> dataMap) {
        Intent intent = new Intent(ACTION_PUSH_NOTIFICATION);
        intent.putExtra(INTENT_SNS_NOTIFICATION_FROM, from);
        intent.putExtra(INTENT_SNS_NOTIFICATION_DATA, dataMap);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Helper method to extract push message from bundle.
     *
     * @param data bundle
     * @return message string from push notification
     */
    public static String getMessage(Bundle data) {
        return ((HashMap) data.get("data")).toString();
    }
}
