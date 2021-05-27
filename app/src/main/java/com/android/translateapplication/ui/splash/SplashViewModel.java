package com.android.translateapplication.ui.splash;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.translateapplication.data.repositories.LanguagesRepo;
import com.android.translateapplication.data.room.LanguagesEntities;

import java.util.ArrayList;
import java.util.List;

public class SplashViewModel extends AndroidViewModel {

    // creating a new variable for course repository.
    private LanguagesRepo repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<LanguagesEntities>> allCourses;

    // constructor for our view modal.
    public SplashViewModel(@NonNull Application application) {
        super(application);
        repository = new LanguagesRepo(application);
        allCourses = repository.getAllLanguages();
    }

    // below method is use to insert the data to our repository.
    public void insert(LanguagesEntities model) {
        repository.insertLanguage(model);
    }

    public void insertAllLanguages(ArrayList<LanguagesEntities> languages){
        repository.insertAllLanguages(languages);
    }


    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.deleteAllLanguages();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<LanguagesEntities>> getAllLanguages() {
        return allCourses;
    }
}
