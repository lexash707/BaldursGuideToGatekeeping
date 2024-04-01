package com.example.baldursguidetogatekeeping.database;

import android.os.AsyncTask;

import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.baldursguidetogatekeeping.model.PlayableCharacter;
import com.example.baldursguidetogatekeeping.model.Spell;
import com.example.baldursguidetogatekeeping.model.Weapon;

import lombok.NonNull;

public class DataSeeder {
    public static void seedDatabase(final Database database) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                PlayableCharacter astarion = new PlayableCharacter("Astarion", "Rogue", "High Elf", "Astarion has the demeanour of a charming and confident nobleman with a biting wit. He enjoys making sarcastic remarks and japes at other people's expense, all the while acting smug and haughty. His decades of being a vampire have left him with a lack of moral scruples compared to the more upstanding members of the party, making him come off as ruthless at best or outright sadistic at worst. While he does attempt to suck the player’s blood non-consensually during one of the first long rests after recruiting him, he does respect the player’s decision regardless of whether it is to allow him to drink or forbid him from drinking their blood.", "astarion.jpg");

                PlayableCharacter karlach = new PlayableCharacter("Karlach", "Barbarian", "Tiefling", "Karlach is an extremely passionate individual who strives to live life to the fullest. She tends to speak excitedly regarding any of life's simple pleasures, whether that be food, drinks, friends, or intimacy. Her demeanor is generally joyous and very forward, having no fear of loudly proclaiming her love of being alive and how much she cares for those close to her. Her positive disposition and infectious energy seem to allow her to make friends easily, even if her honesty can cause her to come off as somewhat naive at times.[note 2] Karlach is also very protective of her friends and companions, taking a genuine interest in them, especially with Wyll should he have been transformed into a devil after defending her", "karlach.jpg");
                PlayableCharacter gale = new PlayableCharacter("Gale", "Wizard", "Human", "Youre a wizard Gale", "gale.jpg");
                Spell spell1 = new Spell("Fireball", 0, "Precise");
                Spell spell2 = new Spell("Cloud of Daggers", 2, "AOE");
                Spell spell3 = new Spell("Bless", 1, "Buff");

                Weapon weapon1 = new Weapon("Dagger", 4, false, "dagger.webp");
                Weapon sword = new Weapon("Sword", 10, false, "sword.webp");
                Weapon bow = new Weapon("Bow", 8, true, "bow.webp");

                database.characterDAO().insertAll(astarion, karlach, gale);
                database.spellDAO().insertAll(spell1, spell2, spell3);
                database.weaponDAO().insertAll(weapon1, sword, bow);

                return null;
            }
        }.execute();
    }

    // Optionally, you can use Room's RoomDatabase.Callback to seed the database during creation
    protected static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Database database = DatabaseClient.getInstance(null).getDatabase();
            seedDatabase((Database) database);
        }
    };
}
