package com.example.baldursguidetogatekeeping;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.adapter.CharacterListAdapter;
import com.example.baldursguidetogatekeeping.database.DatabaseClient;
import com.example.baldursguidetogatekeeping.model.PlayableCharacter;

import java.util.List;

public class CharacterOverview extends AppCompatActivity {

    RecyclerView characterView;

    CharacterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_character_overview);

        characterView = findViewById(R.id.characterView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        characterView.setLayoutManager(layoutManager);

        List<PlayableCharacter> characters = DatabaseClient.getInstance(getApplicationContext())
                .getDatabase()
                .characterDAO()
                .getAll();


        adapter = new CharacterListAdapter(characters, CharacterOverview.this);

        characterView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.spellView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}