package com.example.baldursguidetogatekeeping;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddQuest extends AppCompatActivity {

    EditText nameInput;
    EditText descriptionInput;
    CheckBox primaryInput;
    Button datePicking;
    static TextView pickedDate;
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
        pickedDate = findViewById(R.id.pickedDate);
        save = findViewById(R.id.saveBtn);
        cancel = findViewById(R.id.cancelBtn);

        datePicking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastText = nameInput.getEditableText().toString() + "/" + pickedDate.toString();
                Toast.makeText(AddQuest.this, toastText, Toast.LENGTH_LONG).show();

                Intent data = new Intent();
                data.putExtra("name", nameInput.getEditableText().toString());
                data.putExtra("description", descriptionInput.getEditableText().toString());
                data.putExtra("primary", primaryInput.isChecked());
                data.putExtra("date", pickedDate.getText());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker.
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            pickedDate.setText(IntStream.of(day, month, year).mapToObj(String::valueOf).collect(Collectors.joining("/")));
        }
    }
}