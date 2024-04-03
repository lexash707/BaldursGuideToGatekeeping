package com.example.baldursguidetogatekeeping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baldursguidetogatekeeping.model.Weapon;

import java.util.List;

public class WeaponListAdapter extends RecyclerView.Adapter<WeaponListAdapter.WeaponListHolder> {

    private final List<Weapon> weapons;

    public WeaponListAdapter(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    @NonNull
    @Override
    public WeaponListAdapter.WeaponListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.weapon, parent, false);

        return new WeaponListAdapter.WeaponListHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull WeaponListAdapter.WeaponListHolder holder, int position) {
        holder.weaponName.setText(weapons.get(position).getName());
        holder.damage.setText("DMG: " + weapons.get(position).getBaseDamage());
        holder.ranged.setText(weapons.get(position).isRanged() ? "Ranged" : "Melee");
        holder.avatarW.setImageResource(weapons.get(position).getImagePath());
    }

    @Override
    public int getItemCount() {
        return weapons.size();
    }

    public static class WeaponListHolder extends RecyclerView.ViewHolder {

        public ImageView avatarW;
        public TextView weaponName;
        public TextView damage;
        public TextView ranged;

        public WeaponListHolder(View view) {
            super(view);

            this.avatarW = view.findViewById(R.id.avatarW);
            this.weaponName = view.findViewById(R.id.weaponName);
            this.damage = view.findViewById(R.id.damage);
            this.ranged = view.findViewById(R.id.ranged);
        }
    }
}
