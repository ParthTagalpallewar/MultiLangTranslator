package com.android.translateapplication.ui.translation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.translateapplication.data.repositories.LanguagesRepo;
import com.android.translateapplication.data.room.LanguagesEntities;

import java.util.ArrayList;
import java.util.List;

public class TranslationViewModel extends AndroidViewModel {

    private LanguagesRepo repository;

    private LiveData<List<LanguagesEntities>> allCourses;
    public TranslationViewModel(@NonNull Application application) {
        super(application);
        repository = new LanguagesRepo(application);
        allCourses = repository.getAllLanguages();
    }

    public LiveData<List<LanguagesEntities>> getAllLanguages() {
        return allCourses;
    }






}
