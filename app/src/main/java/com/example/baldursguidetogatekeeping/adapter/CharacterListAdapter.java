package com.example.baldursguidetogatekeeping.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.speech.tts.TextToSpeech;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.R;
import com.example.baldursguidetogatekeeping.model.PlayableCharacter;

import java.util.List;
import java.util.Locale;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterListHolder> {

    private final List<PlayableCharacter> characters;
    private final Activity context;

    public CharacterListAdapter(List<PlayableCharacter> characters, Activity context) {
        this.characters = characters;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterListAdapter.CharacterListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.character, parent, false);

        return new CharacterListAdapter.CharacterListHolder(view, characters);
    }


    @Override
    public void onBindViewHolder(@NonNull CharacterListAdapter.CharacterListHolder holder, int position) {
        holder.name.setText(characters.get(position).getName());
        holder.characterClass.setText(characters.get(position).getCharacterClass());
        holder.race.setText(characters.get(position).getRace());
        holder.avatar.setImageResource(characters.get(position).getImagePath());
        holder.avatar.setOnClickListener(view -> showDialog(characters.get(position).getDescription()));
    }

    private void showDialog(String description) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_layout);

        TextView dialogText = dialog.findViewById(R.id.dialog_text);
        Button ttsButton = dialog.findViewById(R.id.tts_button);
        Button closeButton = dialog.findViewById(R.id.close_button);

        // Set the description text
        dialogText.setText(description);
        dialogText.setMovementMethod(new ScrollingMovementMethod());

        // Initialize TextToSpeech
        final TextToSpeech[] textToSpeech = new TextToSpeech[1];

        // Initialize TextToSpeech
        textToSpeech[0] = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech[0].setLanguage(Locale.getDefault());
                ttsButton.setEnabled(true);
                ttsButton.setOnClickListener(v -> textToSpeech[0].speak(description, TextToSpeech.QUEUE_FLUSH, null, null));
            }
        });

        closeButton.setOnClickListener(v -> {
            if (textToSpeech[0] != null) {
                textToSpeech[0].stop();
                textToSpeech[0].shutdown();
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class CharacterListHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView name;
        public TextView race;
        public TextView characterClass;

        public CharacterListHolder(View view, List<PlayableCharacter> characters) {
            super(view);

            this.avatar = view.findViewById(R.id.avatar);
            this.name = view.findViewById(R.id.name);
            this.race = view.findViewById(R.id.race);
            this.characterClass = view.findViewById(R.id.characterClass);
        }
    }
}
