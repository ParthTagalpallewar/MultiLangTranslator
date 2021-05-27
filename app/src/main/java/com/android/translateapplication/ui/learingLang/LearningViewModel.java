package com.android.translateapplication.ui.learingLang;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.translateapplication.data.repositories.LearningRepository;
import com.android.translateapplication.data.room.SentensesEntaties;
import com.android.translateapplication.data.room.WordsEntities;

import java.util.ArrayList;
import java.util.List;

public class LearningViewModel extends AndroidViewModel {


    private LearningRepository repository;

    private LiveData<List<WordsEntities>> allWords;
    private LiveData<List<SentensesEntaties>> allSentences;

    public LearningViewModel(@NonNull Application application) {
        super(application);
        repository = new LearningRepository(application);
        allWords = repository.getAllWords();
        allSentences = repository.getAllSentences();
    }

    public LiveData<List<WordsEntities>> getAllWords() {
        return allWords;
    }

    public LiveData<List<SentensesEntaties>> getAllSentences() {
        return allSentences;
    }

    public void deleteWords() {

        repository.deleteAllWords();
    }
    public void deleteSentences(){
        repository.deleteAllSentences();
    }

    public void insertAllWords(ArrayList<WordsEntities> wordsList) {
        repository.insertAllWords(wordsList);
    }

    public void insertAllSentences(ArrayList<SentensesEntaties> sentensesEntaties) {
        repository.insertAllSentences(sentensesEntaties);
    }


}
