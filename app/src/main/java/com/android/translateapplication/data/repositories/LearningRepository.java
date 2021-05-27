package com.android.translateapplication.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.android.translateapplication.data.room.LearningDao;
import com.android.translateapplication.data.room.SentensesEntaties;
import com.android.translateapplication.data.room.WordsEntities;
import com.android.translateapplication.data.room.roomDb.DataBase;

import java.util.ArrayList;
import java.util.List;

public class LearningRepository {


    private LearningDao dao;
    private LiveData<List<WordsEntities>> allWords;
    private LiveData<List<SentensesEntaties>> allSentences;

    public LearningRepository(Application application) {
        DataBase database = DataBase.getInstance(application);
        dao = database.learningDao();
        allWords = dao.getAllWords();
        allSentences = dao.getAllSentences();
    }

    //insert all words
    public void insertAllWords(ArrayList<WordsEntities> wordsList) {
        new LearningRepository.InsertAllWordsAsyncTask(dao).execute(wordsList);
    }

    //insert all words
    public void insertAllSentences(ArrayList<SentensesEntaties> sentensesList) {
        new LearningRepository.InsertAllSentencesAsyncTask(dao).execute(sentensesList);
    }

    //DeleteAllLanguages
    public void deleteAllWords() {
        new LearningRepository.DeleteAllWordsAsync(dao).execute();
    }

    //DeleteAllLanguages
    public void deleteAllSentences() { new LearningRepository.DeleteAllSentencesAsync(dao).execute();}

    //getAllWords
    public LiveData<List<WordsEntities>> getAllWords() {
        return allWords;
    }

    //getAllWords
    public LiveData<List<SentensesEntaties>> getAllSentences() {
        return allSentences;
    }

    //Add List of words
    private static class InsertAllWordsAsyncTask extends AsyncTask<ArrayList<WordsEntities>, Void, Void> {
        private LearningDao dao;

        private InsertAllWordsAsyncTask(LearningDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<WordsEntities>... arrayLists) {
            dao.insertAllWords(arrayLists[0]);
            return null;
        }
    }

    //Add List of sentences
    private static class InsertAllSentencesAsyncTask extends AsyncTask<ArrayList<SentensesEntaties>, Void, Void> {
        private LearningDao dao;

        private InsertAllSentencesAsyncTask(LearningDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<SentensesEntaties>... arrayLists) {
            dao.insertAllSentences(arrayLists[0]);
            return null;
        }
    }

    //Delete Words
    private static class DeleteAllWordsAsync extends AsyncTask<Void, Void, Void> {
        private LearningDao dao;

        private DeleteAllWordsAsync(LearningDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllWords();
            return null;
        }
    }

    //Delete Words
    private static class DeleteAllSentencesAsync extends AsyncTask<Void, Void, Void> {
        private LearningDao dao;

        private DeleteAllSentencesAsync(LearningDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllSentence();
            return null;
        }
    }


    //
}
