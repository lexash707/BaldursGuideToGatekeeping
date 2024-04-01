package com.example.baldursguidetogatekeeping.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.baldursguidetogatekeeping.R;

public class QuestReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String questName = intent.getStringExtra("questName");
        String questDescription = intent.getStringExtra("questDescription");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "QUEST_CHANNEL_ID";
        NotificationChannel channel = new NotificationChannel(channelId, "Quest Reminders", NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(channel);

        Notification.Builder builder = new Notification.Builder(context, channelId)
                .setContentTitle(questName)
                .setContentText(questDescription)
                .setSmallIcon(R.drawable.ic_notification) // Ensure you have a drawable resource for the icon
                .setAutoCancel(true);

        notificationManager.notify(0, builder.build());
    }
}
