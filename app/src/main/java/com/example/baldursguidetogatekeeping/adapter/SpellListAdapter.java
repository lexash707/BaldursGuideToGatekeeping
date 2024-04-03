package com.example.baldursguidetogatekeeping.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.R;
import com.example.baldursguidetogatekeeping.dto.Spell;

import java.util.List;

public class SpellListAdapter extends RecyclerView.Adapter<SpellListAdapter.SpellListHolder> {

    private final List<Spell> spells;

    public SpellListAdapter(List<Spell> spells) {
        this.spells = spells;
    }

    @NonNull
    @Override
    public SpellListAdapter.SpellListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.spell, parent, false);

        return new SpellListAdapter.SpellListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpellListAdapter.SpellListHolder holder, int position) {
        holder.spellInfo.setText(spells.get(position).getName() + " / " + spells.get(position).getLevel() + " / " + spells.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return spells.size();
    }

    public static class SpellListHolder extends RecyclerView.ViewHolder {
        public TextView spellInfo;

        public SpellListHolder(View view) {
            super(view);

            this.spellInfo = view.findViewById(R.id.spellInfo);
        }
    }
}
