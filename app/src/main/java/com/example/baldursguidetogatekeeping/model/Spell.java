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
public class Spell {

    @PrimaryKey(autoGenerate = true)
    private Long uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "level")
    private int level;

    @ColumnInfo(name = "type")
    private String type;

    public Spell(String name, int level, String type) {
        this.name = name;
        this.level = level;
        this.type = type;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
