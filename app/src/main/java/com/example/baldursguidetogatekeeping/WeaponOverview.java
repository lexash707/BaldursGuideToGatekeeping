package com.example.baldursguidetogatekeeping;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.adapter.WeaponListAdapter;
import com.example.baldursguidetogatekeeping.database.DatabaseClient;
import com.example.baldursguidetogatekeeping.model.Weapon;

import java.util.List;

public class WeaponOverview extends AppCompatActivity {

    RecyclerView weaponView;

    WeaponListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weapon_overview);

        weaponView = findViewById(R.id.weaponView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        weaponView.setLayoutManager(layoutManager);

        List<Weapon> weapons = DatabaseClient.getInstance(getApplicationContext())
                .getDatabase()
                .weaponDAO()
                .getAll();

        adapter = new WeaponListAdapter(weapons);

        weaponView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.spellView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}