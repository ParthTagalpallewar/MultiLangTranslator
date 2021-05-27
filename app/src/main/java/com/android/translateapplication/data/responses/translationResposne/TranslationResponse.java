package com.android.translateapplication.data.responses.translationResposne;

import java.util.ArrayList;

public class TranslationResponse {

    ArrayList<TranslationModel> translations;

    public TranslationResponse(ArrayList<TranslationModel> translations) {
        this.translations = translations;
    }

    public ArrayList<TranslationModel> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<TranslationModel> translations) {
        this.translations = translations;
    }


}
