package com.android.translateapplication.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefProvider {

    private final String TEXT_TO_CONVERT_KEY = "textToConvert";
    private final String TRANSLATE_FROM = "Translate_From";
    private final String TRANSLATE_TO = "Translate_TO";
    private final String TRANSLATE_FROM_Name = "Translate_From_NAME";
    private final String TRANSLATE_TO_NaME = "Translate_TO_NAME";


    private Context context;
    private SharedPreferences preferences;


    public PrefProvider(Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }


    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void saveTextToSearch(String text) {
        preferences.edit().putString(TEXT_TO_CONVERT_KEY, text).apply();
    }

    public String getTextToSearch() {
        return preferences.getString(TEXT_TO_CONVERT_KEY, null);
    }

    public String getTRANSLATE_FROM() {
        return preferences.getString(TRANSLATE_FROM, null);
    }

    public String getTRANSLATE_TO() {
        return preferences.getString(TRANSLATE_TO, null);
    }

    public String getTRANSLATE_FROM_Name() {
        return preferences.getString(TRANSLATE_FROM_Name, null);
    }

    public String getTRANSLATE_TO_NaME() {
        return preferences.getString(TRANSLATE_TO_NaME, null);
    }

    public void saveTranslateFrom(String text) {
        preferences.edit().putString(TRANSLATE_FROM, text).apply();
    }

    public void saveTranslateTo(String text) {
        preferences.edit().putString(TRANSLATE_TO, text).apply();
    }

    public void saveTranslateFromName(String code, String name) {
        preferences.edit().putString(TRANSLATE_FROM, code).apply();
        preferences.edit().putString(TRANSLATE_FROM_Name, name).apply();
    }

    public void saveTranslateToName(String code, String name) {
        preferences.edit().putString(TRANSLATE_TO, code).apply();
        preferences.edit().putString(TRANSLATE_TO_NaME, name).apply();
    }


}