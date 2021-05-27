package com.android.translateapplication.data.responses.multilanguageResponse;

import com.android.translateapplication.data.responses.translationResposne.TranslationModel;

import java.util.ArrayList;

public class MultiLanguageResponseModel {


    public MultiLanguageResponseModel(ArrayList<TranslationModel> translations) {
        this.translations = translations;
    }

    private ArrayList<TranslationModel> translations;


    public ArrayList<TranslationModel> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<TranslationModel> translations) {
        this.translations = translations;
    }
}
