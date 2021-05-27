package com.android.translateapplication.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.translateapplication.data.room.LanguagesDao;
import com.android.translateapplication.data.room.LanguagesEntities;
import com.android.translateapplication.data.room.roomDb.DataBase;

import java.util.ArrayList;
import java.util.List;

public class LanguagesRepo {

    private LanguagesDao dao;
    private LiveData<List<LanguagesEntities>> allLanguages;

    public LanguagesRepo(Application application) {
        DataBase database = DataBase.getInstance(application);
        dao = database.Dao();
        allLanguages = dao.getAllLanguages();
    }

    //insetSingleLanguage
    public void insertLanguage(LanguagesEntities model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    //insetSingleLanguage
    public void insertAllLanguages(ArrayList<LanguagesEntities> languages) {
        new InsertAllLanguageAsyncTask(dao).execute(languages);
    }

    //DeleteAllLanguages
    public void deleteAllLanguages() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }

    //getAllLanguages
    public LiveData<List<LanguagesEntities>> getAllLanguages() {
        return allLanguages;
    }

    private static class InsertAllLanguageAsyncTask extends AsyncTask<ArrayList<LanguagesEntities>, Void, Void> {
        private LanguagesDao dao;

        private InsertAllLanguageAsyncTask(LanguagesDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<LanguagesEntities>... arrayLists) {
            dao.insertAllLanguages(arrayLists[0]);
            return null;
        }
    }


    private static class InsertCourseAsyncTask extends AsyncTask<LanguagesEntities, Void, Void> {
        private LanguagesDao dao;

        private InsertCourseAsyncTask(LanguagesDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(LanguagesEntities... model) {
            dao.insertLanguages(model[0]);
            return null;
        }
    }


    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private LanguagesDao dao;

        private DeleteAllCoursesAsyncTask(LanguagesDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllLanguages();
            return null;
        }
    }
}