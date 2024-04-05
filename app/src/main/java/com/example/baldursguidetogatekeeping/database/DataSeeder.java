package com.example.baldursguidetogatekeeping.database;

import android.os.AsyncTask;

import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.baldursguidetogatekeeping.R;
import com.example.baldursguidetogatekeeping.model.PlayableCharacter;
import com.example.baldursguidetogatekeeping.model.Weapon;

import lombok.NonNull;

public class DataSeeder {
    public static void seedDatabase(final Database database) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                PlayableCharacter astarion = new PlayableCharacter("Astarion", "Rogue", "High Elf", "Astarion has the demeanour of a charming and confident nobleman with a biting wit. He enjoys making sarcastic remarks and japes at other people's expense, all the while acting smug and haughty. His decades of being a vampire have left him with a lack of moral scruples compared to the more upstanding members of the party, making him come off as ruthless at best or outright sadistic at worst. While he does attempt to suck the player’s blood non-consensually during one of the first long rests after recruiting him, he does respect the player’s decision regardless of whether it is to allow him to drink or forbid him from drinking their blood.", R.mipmap.astarion);

                PlayableCharacter karlach = new PlayableCharacter("Karlach", "Barbarian", "Tiefling", "Karlach is an extremely passionate individual who strives to live life to the fullest. She tends to speak excitedly regarding any of life's simple pleasures, whether that be food, drinks, friends, or intimacy. Her demeanor is generally joyous and very forward, having no fear of loudly proclaiming her love of being alive and how much she cares for those close to her. Her positive disposition and infectious energy seem to allow her to make friends easily, even if her honesty can cause her to come off as somewhat naive at times.[note 2] Karlach is also very protective of her friends and companions, taking a genuine interest in them, especially with Wyll should he have been transformed into a devil after defending her", R.mipmap.karlach);
                PlayableCharacter gale = new PlayableCharacter("Gale", "Wizard", "Human", "You're a wizard Gale", R.mipmap.gale);

                Weapon weapon1 = new Weapon("Dagger", 4, false, R.mipmap.dagger);
                Weapon sword = new Weapon("Sword", 10, false, R.mipmap.sword);
                Weapon bow = new Weapon("Bow", 8, true, R.mipmap.bow);

                database.characterDAO().insertAll(astarion, karlach, gale);
                database.weaponDAO().insertAll(weapon1, sword, bow);

                return null;
            }
        }.execute();
    }

        protected static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Database database = DatabaseClient.getInstance(null).getDatabase();
            seedDatabase(database);
        }
    };
}
