package com.example.baldursguidetogatekeeping.util;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line
                context.startActivity(intent);
                return; // Stop further execution since we can't schedule the alarm yet
            }
        }

        Intent intent = new Intent(context, QuestReminderReceiver.class);
        intent.putExtra("questName", quest.getName());
        intent.putExtra("questDescription", quest.getDescription());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, quest.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, quest.getDateToDo().getTime(), pendingIntent);
        }
    }
}
