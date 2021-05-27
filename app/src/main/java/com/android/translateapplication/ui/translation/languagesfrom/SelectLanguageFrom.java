package com.android.translateapplication.ui.translation.languagesfrom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.android.translateapplication.R;
import com.android.translateapplication.data.pref.PrefProvider;
import com.android.translateapplication.data.room.LanguagesEntities;
import com.android.translateapplication.ui.translation.TranslationViewModel;
import com.android.translateapplication.ui.translation.TraslationActivity;
import com.android.translateapplication.ui.translation.languages.LanguageAdapterTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectLanguageFrom extends AppCompatActivity implements LanguageAdapterFrom.OnLanguageClickListener {

    private TranslationViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private EditText mSearchView;
    private LanguageAdapterFrom mAdapter;
    private List<LanguagesEntities> languagesEntitiesList;

    public static String EXTRA_KEY_LANG_CODE_FROM = "EXTRA_KEY_LANGUAGE_CODE_FROM";
    public static String EXTRA_KEY_LANG_NAME_FROM = "EXTRA_KEY_LANGUAGE_NAME_FROM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language_activiry);

        initViews();

        setUpRecyclerView();

        searchFunctionality();
    }

    private void searchFunctionality() {

        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {

        ArrayList<LanguagesEntities> filteredList  = new  ArrayList();
        for (int i = 0;i < languagesEntitiesList.size();i++) {
            if (languagesEntitiesList.get(i).getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(languagesEntitiesList.get(i));
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void setUpRecyclerView() {
        mViewModel.getAllLanguages().observe(this,lang -> {
            languagesEntitiesList = lang;
            mAdapter = new LanguageAdapterFrom(lang,this::onClick);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

    private void initViews() {
        mViewModel = new ViewModelProvider(this).get(TranslationViewModel.class);
        mRecyclerView = findViewById(R.id.select_language_recyclerView);
        mSearchView = findViewById(R.id.select_language_searchView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    public void onClick(LanguagesEntities entities) {
        Intent translatorEvent = new Intent(this, TraslationActivity.class);
        translatorEvent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Save Name and Code of Converstion from language
        PrefProvider prefProvider = new PrefProvider(this);
        prefProvider.saveTranslateFromName(entities.getCode(),entities.getName());

        startActivity(translatorEvent);
    }


}