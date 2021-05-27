package com.android.translateapplication.ui.learingLang.viewAdapter.words;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.translateapplication.R;
import com.android.translateapplication.data.pref.LanguagePrefManager;
import com.android.translateapplication.data.room.WordsEntities;
import com.android.translateapplication.ui.learingLang.LearningViewModel;
import com.android.translateapplication.ui.translation.TranslationViewModel;

import java.util.Locale;


public class WordsLearningFragment extends Fragment implements WordsRecyclerAdapter.OnWordClickListener{

    LearningViewModel mViewMdoel;
    RecyclerView mRecyclerView;
    TextToSpeech textToSpeech;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_words,container,true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        LanguagePrefManager prefManager = new LanguagePrefManager(requireContext());

        String nativeLanguage = prefManager.getNativeLanguageName();
        String desireLanguage = prefManager.getLanguageToLearnName();

        mViewMdoel.getAllWords().observe(getViewLifecycleOwner(),wordsEntities -> {
            WordsRecyclerAdapter adapter = new WordsRecyclerAdapter(wordsEntities,this::onClick,nativeLanguage,desireLanguage);
            mRecyclerView.setAdapter(adapter);
        });

    }

    private void initViews(View view) {
        mViewMdoel = new ViewModelProvider(this).get(LearningViewModel.class);
        mRecyclerView = view.findViewById(R.id.words_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.setHasFixedSize(true);

        // create an object textToSpeech and adding features into it
        textToSpeech = new TextToSpeech(requireContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    @Override
    public void onClick(WordsEntities entities) {
        textToSpeech.speak(entities.getDesireText(), TextToSpeech.QUEUE_FLUSH, null);
    }
}
