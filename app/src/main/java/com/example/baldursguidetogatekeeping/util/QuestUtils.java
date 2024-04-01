package com.example.baldursguidetogatekeeping.util;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.baldursguidetogatekeeping.Quest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QuestUtils {

    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static List<Quest> readQuestsFromFile(Context context, String filename) throws IOException {
        List<Quest> quests = new ArrayList<>();
        try (FileInputStream fis = context.openFileInput(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            quests = reader.lines()
                    .map(line -> line.split(",", -1))
                    .map(tokens -> new Quest(
                            tokens[0],
                            tokens[1],
                            parseDate(tokens[2]),
                            Boolean.parseBoolean(tokens[3])))
                    .collect(Collectors.toList());

            quests.forEach(quest -> {
                QuestUtils.scheduleQuestNotification(context, quest);
            });

        }
        return quests;
    }

    private static Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeQuestsToFile(Context context, String filename, List<Quest> quests) throws IOException {
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            quests.forEach(quest -> {
                String line = String.format("%s,%s,%s,%b\n",
                        quest.getName(),
                        quest.getDescription(),
                        dateFormat.format(quest.getDateToDo()),
                        quest.isPrimary());
                try {
                    writer.write(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }
    }


    public static void scheduleQuestNotification(Context context, Quest quest) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) return; // Early exit if alarmManager is null

        // Check if the app targets Android 12 (S) or higher and whether it can schedule exact alarms
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            // If not, prompt the user to allow the permission in settings
            Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return; // Exit the method to prevent further execution
        }

        long triggerAtMillis = quest.getDateToDo().getTime() + 3900000;
        long currentTimeMillis = System.currentTimeMillis();

        // If the scheduled time is in the past, adjust it to trigger a short time in the future
        if (triggerAtMillis <= currentTimeMillis) {
            triggerAtMillis = currentTimeMillis + 60000; // For example, adjust to fire in 60 seconds
        }

        Intent intent = new Intent(context, QuestReminderReceiver.class);
        intent.putExtra("questName", quest.getName());
        intent.putExtra("questDescription", quest.getDescription());

        // Use a unique request code for the PendingIntent to ensure it's a distinct alarm
        int requestCode = (quest.getName() + quest.getDateToDo().toString()).hashCode();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Schedule the exact alarm
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);

        Log.i(TAG, "NANANANANANANANANANANANANANAN SISBOOOOOOOOOOOC");
    }

}
