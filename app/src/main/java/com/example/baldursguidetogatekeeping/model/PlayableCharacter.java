package com.example.baldursguidetogatekeeping.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "playable_character")
public class PlayableCharacter {

    @PrimaryKey(autoGenerate = true)
    private Long uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "character_class")
    private String characterClass;

    @ColumnInfo(name = "race")
    private String race;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "image_path")
    private String imagePath;

    public PlayableCharacter(String name, String characterClass, String race, String description, String imagePath) {
        this.name = name;
        this.characterClass = characterClass;
        this.race = race;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getter and setter methods for each field
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

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
