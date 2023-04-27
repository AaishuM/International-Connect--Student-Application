package com.uniguides.userscampusapply.User;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.uniguides.userscampusapply.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
        private static final String CHANNEL_ID = "Channel_ID";

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);

            if (remoteMessage.getNotification() != null) {

                // playing audio and vibration when user sees the notification
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    r.setLooping(false);
                }

                // vibration
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long[] pattern = {100, 300, 300, 300};
                v.vibrate(pattern, -1);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.user_name_icon)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setAutoCancel(true);

                Intent resultIntent = new Intent(this, UserDashboard.class);
                resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                notificationBuilder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Name", NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(channel);
                }
                notificationManager.notify(0, notificationBuilder.build());
            }

            if (remoteMessage.getNotification() != null) {

                String message = remoteMessage.getNotification().getBody();

                // Create an intent to launch the NotificationActivity
                Intent intent = new Intent(this, Notifiations.class);
                intent.putExtra("message", message);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Create a PendingIntent and set it to the notification
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

                // Create the notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.user_name_icon)
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

                // Show the notification
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());
            }
        }
    }


