package com.example.baldursguidetogatekeeping;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Schedule extends AppCompatActivity {

    private static final String TAG = "QuestList";

    RecyclerView recyclerView;
    Button addQuestButton;
    QuestListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        addQuestButton = (Button) findViewById(R.id.addQuestButton);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Quest> quests =  new ArrayList<>();

        addQuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered quest creator");
                Intent intent = new Intent(Schedule.this, AddQuest.class);
                startActivityForResult(intent, 1);
            }
        });

        adapter = new QuestListAdapter(quests);

        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 1){
            Log.i(TAG, "Quest added");
            Quest newQuest =  new Quest();
            newQuest.setPrimary(data.getBooleanExtra("primary",false));
            newQuest.setName(data.getStringExtra("name"));
            newQuest.setDescription(data.getStringExtra("description"));

            String dateString = data.getStringExtra("date");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            try {
                newQuest.setDateToDo(sdf.parse(dateString));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            adapter.add(newQuest);

        }
    }
}