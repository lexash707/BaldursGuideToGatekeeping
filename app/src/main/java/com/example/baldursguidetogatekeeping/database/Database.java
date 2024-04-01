package com.example.baldursguidetogatekeeping.database;

import androidx.room.RoomDatabase;

import com.example.baldursguidetogatekeeping.dao.PlayableCharacterDAO;
import com.example.baldursguidetogatekeeping.dao.SpellDAO;
import com.example.baldursguidetogatekeeping.dao.WeaponDAO;
import com.example.baldursguidetogatekeeping.model.PlayableCharacter;
import com.example.baldursguidetogatekeeping.model.Spell;
import com.example.baldursguidetogatekeeping.model.Weapon;

@androidx.room.Database(entities = {PlayableCharacter.class, Weapon.class, Spell.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract WeaponDAO weaponDAO();

    public abstract PlayableCharacterDAO characterDAO();

    public abstract SpellDAO spellDAO();
}
