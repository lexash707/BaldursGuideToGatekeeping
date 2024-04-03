package com.example.baldursguidetogatekeeping;

import android.os.Bundle;
import android.os.StrictMode;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.adapter.SpellListAdapter;
import com.example.baldursguidetogatekeeping.dto.Spell;
import com.example.baldursguidetogatekeeping.util.SpellService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SpellOverview extends AppCompatActivity {

    RecyclerView spellView;

    SpellListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spell_overview);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        spellView = findViewById(R.id.spellView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        spellView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://astarionsupremacy.morava.in.rs:8080")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        SpellService service = retrofit.create(SpellService.class);

        List<Spell> spells = new ArrayList<>();

        try {
            spells = service.getAll().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        adapter = new SpellListAdapter(spells);

        spellView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.spellView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}