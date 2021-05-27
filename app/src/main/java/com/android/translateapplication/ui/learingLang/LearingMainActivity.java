package com.android.translateapplication.ui.learingLang;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.android.translateapplication.R;
import com.android.translateapplication.ui.learingLang.viewAdapter.*;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LearingMainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learing_main);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);


        viewPager.setAdapter(new ViewPagerAdapter(this));

        viewPager.setUserInputEnabled(false);

        new TabLayoutMediator(tabLayout, viewPager,(tab, position) -> {
            if (position == 0){
                tab.setText("Words");
            }else{
                tab.setText("Phrases");
            }
        }).attach();

    }

}