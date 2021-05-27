package com.android.translateapplication.data.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "course_table")
public class LanguagesEntities {



    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String nativeName;
    private String dir;
    private String code;

    public void Language(int id,String name, String nativeName, String dir) {
        this.id = id;
        this.name = name;
        this.nativeName = nativeName;
        this.dir = dir;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getDir() {
        return dir;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
