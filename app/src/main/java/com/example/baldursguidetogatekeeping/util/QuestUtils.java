package com.example.baldursguidetogatekeeping.util;

import android.annotation.SuppressLint;
import android.content.Context;

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
}
