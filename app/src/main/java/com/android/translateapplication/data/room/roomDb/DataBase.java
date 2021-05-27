package com.android.translateapplication.data.room.roomDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.translateapplication.data.repositories.LearningRepository;
import com.android.translateapplication.data.room.LanguagesDao;
import com.android.translateapplication.data.room.LanguagesEntities;
import com.android.translateapplication.data.room.LearningDao;
import com.android.translateapplication.data.room.SentensesEntaties;
import com.android.translateapplication.data.room.WordsEntities;

@Database(entities = {LanguagesEntities.class, WordsEntities.class, SentensesEntaties.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    private static DataBase instance;
    public abstract LanguagesDao Dao();
    public abstract LearningDao learningDao();

    public static synchronized DataBase getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class, "course_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(DataBase instance) {
            LanguagesDao dao = instance.Dao();
            LearningDao learningDao = instance.learningDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}