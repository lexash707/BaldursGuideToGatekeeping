package com.example.baldursguidetogatekeeping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.model.PlayableCharacter;

import java.util.List;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterListHolder> {

    private final List<PlayableCharacter> characters;

    public CharacterListAdapter(List<PlayableCharacter> characters) {
        this.characters = characters;
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
