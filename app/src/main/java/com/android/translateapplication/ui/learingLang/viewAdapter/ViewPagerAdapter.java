package com.android.translateapplication.ui.learingLang.viewAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.translateapplication.ui.learingLang.viewAdapter.sentences.SentenceLearingFragment;
import com.android.translateapplication.ui.learingLang.viewAdapter.words.WordsLearningFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    final public static int MY_WORDS_FRAGMENT = 0;
    final public static int MY_SENTENCE_FRAGMENT = 1;


    public ViewPagerAdapter(@NonNull AppCompatActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == MY_SENTENCE_FRAGMENT){
            return new SentenceLearingFragment();
        }else{
            return new WordsLearningFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}