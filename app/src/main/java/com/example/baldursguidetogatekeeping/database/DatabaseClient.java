package com.example.baldursguidetogatekeeping.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private static DatabaseClient instance;
    private final Database appDatabase;

    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, Database.class, "baldur_data").allowMainThreadQueries().addCallback(DataSeeder.databaseCallback).build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public Database getDatabase() {
        return appDatabase;
    }
}
