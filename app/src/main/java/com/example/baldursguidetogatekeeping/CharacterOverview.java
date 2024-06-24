package com.example.baldursguidetogatekeeping;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.adapter.CharacterListAdapter;
import com.example.baldursguidetogatekeeping.database.DatabaseClient;
import com.example.baldursguidetogatekeeping.model.PlayableCharacter;

import java.util.List;

public class CharacterOverview extends AppCompatActivity {

    RecyclerView characterView;
    CharacterListAdapter adapter;
    Button addCharBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_overview);


        EdgeToEdge.enable(this);


        characterView = findViewById(R.id.characterView);
        addCharBtn = findViewById(R.id.addCharBtn);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        characterView.setLayoutManager(layoutManager);


        List<PlayableCharacter> characters = DatabaseClient.getInstance(getApplicationContext())
                .getDatabase()
                .characterDAO()
                .getAll();


        adapter = new CharacterListAdapter(characters, CharacterOverview.this);
        characterView.setAdapter(adapter);


//        addCharBtn.setOnClickListener(view -> showDialog());
    }


//    private void showDialog() {
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_new_character);
//
//        // Initialize views in the dialog
//        EditText editTextName = dialog.findViewById(R.id.editTextName);
//        EditText editTextRace = dialog.findViewById(R.id.editTextRace);
//        EditText editTextClass = dialog.findViewById(R.id.editTextClass);
//        ImageView imageView = dialog.findViewById(R.id.imageView);
//        Button selectImageButton = dialog.findViewById(R.id.selectImageButton);
//        Button saveButton = dialog.findViewById(R.id.saveButton);
//
//        Bitmap selectedBitmap = null;

//        selectImageButton.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            imageResultLauncher.launch(intent);
//        });
//
//        saveButton.setOnClickListener(v -> {
//            // Retrieve user input
//            String name = editTextName.getText().toString().trim();
//            String race = editTextRace.getText().toString().trim();
//            String charClass = editTextClass.getText().toString().trim();
//
//            // Validate input fields
//            if (name.isEmpty() || race.isEmpty() || charClass.isEmpty()) {
//                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // Convert selected image to byte array
//            byte[] imageData = null;
//            if (selectedBitmap != null) {
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                selectedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                imageData = stream.toByteArray();
//            }
//
//            PlayableCharacter character = new PlayableCharacter(name, race, charClass, imageData);
//            long id = DatabaseClient.getInstance(getApplicationContext())
//                    .getDatabase()
//                    .characterDAO()
//                    .insert(character);
//
//            // Update RecyclerView with the new character
//            character.setId(id); // Set ID generated by Room database
//            adapter.addCharacter(character);
//            adapter.notifyDataSetChanged();
//
//            // Dismiss dialog
//            dialog.dismiss();
//        });
//
//        // Show the dialog
//        dialog.show();
//    }
//
//    private final ActivityResultLauncher<Intent> imageResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Intent data = result.getData();
//                    try {
//                        // Retrieve selected image URI
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(
//                                getContentResolver(), data.getData());
//
//                        // Display selected image in the dialog
//                        ImageView imageView = dialog.findViewById(R.id.imageView);
//                        imageView.setImageBitmap(bitmap);
//                        imageView.setVisibility(View.VISIBLE);
//
//                        // Store selected image bitmap
//                        selectedBitmap = bitmap;
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
}
