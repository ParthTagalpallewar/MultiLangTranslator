package com.android.translateapplication.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LanguagePrefManager {

    private Context context;
    private SharedPreferences preferences;

    private final String NATIVE_LANG_PREF = "nativeLanguageTextToConvert";
    private final String LEARN_LANG_PREF = "LEARNLanguageTextToConvert";
    private final String NATIVE_LANG_PREF_NAME = "nativeLanguageTextToConvertName";
    private final String LEARN_LANG_PREF_NAME = "LEARNLanguageTextToConvertName";


    public LanguagePrefManager(Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }


   /* public String getTextToSearch() {
        return preferences.getString(TEXT_TO_CONVERT_KEY, null);
    }*/

    //Codes
    public void addNativeLanguage(String nativeLanguage) {
        preferences.edit().putString(NATIVE_LANG_PREF, nativeLanguage).apply();
    }

    public void addLearningLanguage(String langToLearn) {
        preferences.edit().putString(LEARN_LANG_PREF, langToLearn).apply();
    }

    //Names
    public void addNativeLanguageName(String nativeLanguageName) {
        preferences.edit().putString(NATIVE_LANG_PREF_NAME, nativeLanguageName).apply();
    }

    public void addLearningLanguageName(String langToLearnName) {
        preferences.edit().putString(LEARN_LANG_PREF_NAME, langToLearnName).apply();
    }

    //getting Codes
    public String getNativeLanguage() {
        return preferences.getString(NATIVE_LANG_PREF, null);
    }

    public String getLanguageToLearn() {
        return preferences.getString(LEARN_LANG_PREF, null);
    }

    //getting Names
    public String getNativeLanguageName() {
        return preferences.getString(NATIVE_LANG_PREF_NAME, null);
    }

    public String getLanguageToLearnName() {
        return preferences.getString(LEARN_LANG_PREF_NAME, null);
    }

}