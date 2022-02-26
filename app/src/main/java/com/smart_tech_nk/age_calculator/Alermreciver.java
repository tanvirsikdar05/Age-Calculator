package com.smart_tech_nk.age_calculator;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Alermreciver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // Get id & message
       /* int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("message");

        // Call MainActivity when notification is tapped.
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        // NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For API 26 and above
            CharSequence channelName = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }*/

        // Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "tanvirid")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Today someone birthday/anniversary")
                .setContentText("Check event")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Notify
        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }
}
