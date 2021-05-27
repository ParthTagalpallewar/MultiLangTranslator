package com.android.translateapplication.data.room;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@androidx.room.Dao
public interface LearningDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllWords(ArrayList<WordsEntities> words);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllSentences(ArrayList<SentensesEntaties> sentences);

    @Query("DELETE FROM words_table")
    void deleteAllWords();

    @Query("DELETE FROM sentence_table")
    void deleteAllSentence();

    @Query("SELECT * FROM words_table")
    LiveData<List<WordsEntities>> getAllWords();

    @Query("SELECT * FROM sentence_table")
    LiveData<List<SentensesEntaties>> getAllSentences();
}
