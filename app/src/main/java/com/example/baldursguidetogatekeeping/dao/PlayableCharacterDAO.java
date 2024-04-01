package com.example.baldursguidetogatekeeping.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.baldursguidetogatekeeping.model.PlayableCharacter;

import java.util.List;

@Dao
public interface PlayableCharacterDAO {
    @Query("SELECT * FROM playable_character")
    List<PlayableCharacter> getAll();

    @Query("SELECT * FROM playable_character WHERE uid = :id LIMIT 1")
    PlayableCharacter getById(Long id);

    @Insert
    void insertAll(PlayableCharacter... characters);

    @Delete
    void delete(PlayableCharacter character);
}
