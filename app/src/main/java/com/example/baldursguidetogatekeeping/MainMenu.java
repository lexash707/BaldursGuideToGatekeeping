package com.example.baldursguidetogatekeeping;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity {

    private static final int SCHEDULE_REQUEST_CODE = 1;

    Button characters;
    Button weapons;
    Button spells;

    Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        deleteDatabase("baldur_data");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        schedule = findViewById(R.id.scheduleBtn);
        schedule.setOnClickListener(v -> {
            Log.i(TAG, "entered quest list");
            Intent intent = new Intent(MainMenu.this, Schedule.class);
            startActivity(intent);
        });

        characters = findViewById(R.id.characterMenuBtn);

        characters.setOnClickListener(v -> {
            Log.i(TAG, "entered quest list");
            Intent intent = new Intent(MainMenu.this, CharacterOverview.class);
            startActivity(intent);
        });

        weapons = findViewById(R.id.weaponMenuBtn);

        weapons.setOnClickListener(v -> {
            Log.i(TAG, "entered quest list");
            Intent intent = new Intent(MainMenu.this, WeaponOverview.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}