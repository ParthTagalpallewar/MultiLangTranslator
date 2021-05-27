package com.android.translateapplication.ui.translation.languages;

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

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectLanguageTo extends AppCompatActivity implements LanguageAdapterTo.OnLanguageClickListener {

    private TranslationViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private EditText mSearchView;
    private LanguageAdapterTo mAdapter;
    private List<LanguagesEntities> languagesEntitiesList;



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
            mAdapter = new LanguageAdapterTo(lang,this::onClick);
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

        //Save Name and Code of Converstion to language
        PrefProvider prefProvider = new PrefProvider(this);
        prefProvider.saveTranslateToName(entities.getCode(),entities.getName());

        startActivity(translatorEvent);
    }


}