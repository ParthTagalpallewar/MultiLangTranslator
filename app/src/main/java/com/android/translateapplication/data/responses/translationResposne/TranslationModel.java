package com.android.translateapplication.data.responses.translationResposne;

public class TranslationModel {

    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TranslationModel(String text) {
        this.text = text;
    }

}
