package com.example.baldursguidetogatekeeping.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.baldursguidetogatekeeping.model.Spell;

import java.util.List;

@Dao
public interface SpellDAO {

    @Query("SELECT * FROM spell")
    List<Spell> getAll();

    @Query("SELECT * FROM spell WHERE uid = (:id) LIMIT 1")
    Spell getById(Long id);

    @Insert
    void insertAll(Spell... spells);

    @Delete
    void delete(Spell spell);
}
