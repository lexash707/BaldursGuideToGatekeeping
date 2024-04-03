package com.example.baldursguidetogatekeeping.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Weapon {

    @PrimaryKey(autoGenerate = true)
    private Long uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "base_damage")
    private int baseDamage;

    @ColumnInfo(name = "ranged")
    private boolean ranged;

    @ColumnInfo(name = "image")
    private int imagePath;


    public Weapon(String name, int baseDamage, boolean ranged, int imagePath) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.ranged = ranged;
        this.imagePath = imagePath;
    }

    public Long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public boolean isRanged() {
        return ranged;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
