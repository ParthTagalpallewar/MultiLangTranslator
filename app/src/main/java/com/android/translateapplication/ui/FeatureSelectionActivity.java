package com.android.translateapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.android.translateapplication.R;
import com.android.translateapplication.data.pref.LanguagePrefManager;
import com.android.translateapplication.data.pref.PrefProvider;
import com.android.translateapplication.ui.learingLang.BaseLearingActivity;
import com.android.translateapplication.ui.learingLang.OtherApps;
import com.android.translateapplication.ui.translation.TraslationActivity;

public class FeatureSelectionActivity extends AppCompatActivity {

    LinearLayout learningLang, translateText;
    ImageView otherApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_selection);

        initViews();

        translateTextBtnClicked();

        learningLangBtnClicked();

        otherApps.setOnClickListener(v -> {
            Intent i = new Intent(this, OtherApps.class);
            startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Clearing all values from preferences
        PrefProvider pref = new PrefProvider(this);

        pref.saveTextToSearch(null);
        pref.saveTranslateFromName(null, null);
        pref.saveTranslateToName(null, null);



    }

    private void learningLangBtnClicked() {
        learningLang.setOnClickListener(v -> {
            Intent learningIntent = new Intent(this, BaseLearingActivity.class);
            startActivity(learningIntent);
        });
    }

    private void translateTextBtnClicked() {
        translateText.setOnClickListener(v -> {
            Intent translateIntent = new Intent(this, TraslationActivity.class);
            startActivity(translateIntent);
        });
    }

    private void initViews() {
        learningLang = findViewById(R.id.liner_lang_learing);
        translateText = findViewById(R.id.liner_text_translation);
        otherApps = findViewById(R.id.otherApps);
    }
}