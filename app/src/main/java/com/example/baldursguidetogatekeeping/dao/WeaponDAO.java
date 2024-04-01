package com.example.baldursguidetogatekeeping.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.baldursguidetogatekeeping.model.Weapon;

import java.util.List;

@Dao
public interface WeaponDAO {

    @Query("SELECT * FROM weapon")
    List<Weapon> getAll();

    @Query("SELECT * FROM weapon WHERE uid = (:id) LIMIT 1")
    Weapon getById(Long id);

    @Insert
    void insertAll(Weapon... weapons);

    @Delete
    void delete(Weapon weapon);
}
