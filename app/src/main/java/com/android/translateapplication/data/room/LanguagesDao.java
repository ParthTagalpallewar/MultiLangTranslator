package com.android.translateapplication.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@androidx.room.Dao
public interface LanguagesDao {

    @Insert
    void insertLanguages(LanguagesEntities language);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLanguages(ArrayList<LanguagesEntities> languages);

    @Query("DELETE FROM course_table")
    void deleteAllLanguages();

    @Query("SELECT * FROM course_table")
    LiveData<List<LanguagesEntities>> getAllLanguages();
}
