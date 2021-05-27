package com.android.translateapplication.data.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "words_table")
public class WordsEntities {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public String getNativeText() {
        return nativeText;
    }

    public void setNativeText(String nativeText) {
        this.nativeText = nativeText;
    }

    public String getDesireText() {
        return desireText;
    }

    public void setDesireText(String desireText) {
        this.desireText = desireText;
    }

    public WordsEntities() {

    }


    public WordsEntities(int id, String englishText, String nativeText, String desireText) {
        this.id = id;
        this.englishText = englishText;
        this.nativeText = nativeText;
        this.desireText = desireText;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String englishText;
    private String nativeText;
    private String desireText;


}
