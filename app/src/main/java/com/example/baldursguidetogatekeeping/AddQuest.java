package com.example.baldursguidetogatekeeping;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddQuest extends AppCompatActivity {

    EditText nameInput;
    EditText descriptionInput;
    CheckBox primaryInput;
    Button datePicking;
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_quest);

        nameInput = findViewById(R.id.questName);
        descriptionInput = findViewById(R.id.descriptionInput);
        primaryInput = findViewById(R.id.primaryCheckBox);
        datePicking = findViewById(R.id.datePickBtn);
        save = findViewById(R.id.saveBtn);
        cancel = findViewById(R.id.cancelBtn);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}