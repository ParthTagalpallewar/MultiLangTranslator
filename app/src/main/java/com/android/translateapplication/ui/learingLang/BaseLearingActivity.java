package com.android.translateapplication.ui.learingLang;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.android.translateapplication.R;
import com.android.translateapplication.data.pref.LanguagePrefManager;
import com.android.translateapplication.data.responses.multilanguageResponse.MultiLanguageResponseModel;
import com.android.translateapplication.data.responses.translationResposne.TranslationModel;
import com.android.translateapplication.data.retrofit.RetrofitClient;
import com.android.translateapplication.data.room.SentensesEntaties;
import com.android.translateapplication.data.room.WordsEntities;
import com.android.translateapplication.ui.learingLang.languages.learnLangChoose.ChooseLearnLanguage;
import com.android.translateapplication.ui.learingLang.languages.nativeLanguage.ChooseNativeLanguage;
import com.android.translateapplication.ui.splash.SplashScreen;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.translateapplication.data.Constants.API_VERSION;

public class BaseLearingActivity extends AppCompatActivity {

    private static final String TAG = "BaseLearingActivity";
    LottieAnimationView animationView;
    LinearLayout chooseNativeLangBtn, chooseLearnLangBtn, startLearningLanguage;

    TextView tvNativeLang, tvLangToLearn;
    private LearningViewModel viewModel;

    Boolean gotWords = false;
    Boolean gotSentece = false;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_learing);


        initViews();

        chooseNativeLangBtn.setOnClickListener(v -> {
            Intent learningIntent = new Intent(this, ChooseNativeLanguage.class);
            startActivity(learningIntent);
        });

        chooseLearnLangBtn.setOnClickListener(v -> {
            Intent learningIntent = new Intent(this, ChooseLearnLanguage.class);
            startActivity(learningIntent);
        });

        startLearningLanguage.setOnClickListener(v -> {
            LanguagePrefManager prefManager = new LanguagePrefManager(this);

            String nativeLangName = prefManager.getNativeLanguage();
            String langToLearn = prefManager.getLanguageToLearn();

            if (nativeLangName == null) {
                toast("Please Choose Your Native Language First");

            }
            if (langToLearn == null) {

                toast("Please Choose the Language You wanted to Learn");
            } else {
                getAllWords(nativeLangName, langToLearn);
                getAllSentences(nativeLangName, langToLearn);
            }
        });




    }

    //todo handle all errors
    private void getAllWords(String nativeLangName, String langToLearn) {

        progressbar(true);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Good morning ");
        strings.add("Good afternoon ");
        strings.add("Good evening ");
        strings.add("Good night ");
        strings.add("Hello");
        strings.add("Bye ");
        strings.add("Please ");
        strings.add("Thank you ");
        strings.add("Welcome ");
        strings.add("What");
        strings.add("Which");
        strings.add("Who");
        strings.add("How");
        strings.add("When");
        strings.add("Where");
        strings.add("Why");
        strings.add("Come");
        strings.add("Do");
        strings.add("Go");
        strings.add("Have");
        strings.add("Help");
        strings.add("Like");
        strings.add("Live");
        strings.add("Need");
        strings.add("Think");
        strings.add("Can");


        Call<ArrayList<MultiLanguageResponseModel>> responseModelCall =
                RetrofitClient.getInstance().getMyApi().getMultiLangTranslation
                        (API_VERSION, "en", nativeLangName, langToLearn, "en", getJsonBody(strings));

        responseModelCall.enqueue(new Callback<ArrayList<MultiLanguageResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MultiLanguageResponseModel>> call, Response<ArrayList<MultiLanguageResponseModel>> response) {
                if (response.isSuccessful() && response.code() == 200) {

                    ArrayList<MultiLanguageResponseModel> listMultiLangResponse = response.body();

                    if (listMultiLangResponse != null && !listMultiLangResponse.isEmpty()) {

                        ArrayList<WordsEntities> wordsList = new ArrayList();

                        for (int i = 0; i < listMultiLangResponse.size(); i++) {
                            //   Log.e(TAG, "onResponse: "+listMultiLangResponse.get(i).toString() );
                            ArrayList<TranslationModel> multiLanguage = listMultiLangResponse.get(i).getTranslations();

                            WordsEntities wordRow = new WordsEntities();

                            for (int j = 0; j < multiLanguage.size(); j++) {
                                wordRow.setId(i);
                                if (j % 3 == 0) {
                                    wordRow.setEnglishText(multiLanguage.get(j).getText());
                                } else if (j % 2 == 0) {
                                    wordRow.setDesireText(multiLanguage.get(j).getText());
                                } else {
                                    wordRow.setNativeText(multiLanguage.get(j).getText());
                                }
                            }

                            wordsList.add(wordRow);

                        }

                        viewModel.deleteWords();
                        viewModel.insertAllWords(wordsList);

                        gotWords = true;
                        checkNavigation();
                    }

                } else {
                    toast("onResponse" + response.toString());
                    try {
                        Log.e("TAG", "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MultiLanguageResponseModel>> call, Throwable t) {
                if (t.toString().contains("java.net.UnknownHostException")){
                 toast("Please Check Your Internet Connection");
                }else{
                 toast("Failure - " + t.toString());

                }
            }
        });

    }

    private void getAllSentences(String nativeLangName, String langToLearn) {

        progressbar(true);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("A lovely day, isn’t it?");
        strings.add("Do i have to?");
        strings.add("Can I help you?");
        strings.add("How are things going?");
        strings.add("Any thing else?");
        strings.add("Are you kidding?");
        strings.add("Are you sure?");
        strings.add("Do you understand me?");
        strings.add("Are you done?");
        strings.add("Can I ask you something?");
        strings.add("Can you please repeat that?");
        strings.add("Did you get it?");
        strings.add("Do you need anything?");
        strings.add("How are you?");
        strings.add("How do you feel?");
        strings.add("How much is it?");
        strings.add("How old are you?");
        strings.add("How was your weekend?");
        strings.add("Is all good?");
        strings.add("Is everything OK?");
        strings.add("What are you doing?");
        strings.add("What are you talking about?");
        strings.add("What are you up to?");
        strings.add("What are your hobbies?");
        strings.add("What did you say?");
        strings.add("What do you need?");
        strings.add("What do you think?");
        strings.add("What do you want to do?");
        strings.add("What do you want?");
        strings.add("What’s the weather like?");
        strings.add("What’s your e-mail address?");
        strings.add("What is your job?");
        strings.add("What’s your name?");
        strings.add("What’s your phone number?");
        strings.add("What is going on?");
        strings.add("When is the train leaving?");
        strings.add("How can I go to the town centre?");
        strings.add("Where are you from?");
        strings.add("Where are you going?");
        strings.add("Where are you?");
        strings.add("Where did you get it?");
        strings.add("Where do you live?");
        strings.add("Are you coming with me?");
        strings.add("How long will you stay?");


        Call<ArrayList<MultiLanguageResponseModel>> responseModelCall =
                RetrofitClient.getInstance().getMyApi().getMultiLangTranslation
                        (API_VERSION, "en", nativeLangName, langToLearn, "en", getJsonBody(strings));

        responseModelCall.enqueue(new Callback<ArrayList<MultiLanguageResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MultiLanguageResponseModel>> call, Response<ArrayList<MultiLanguageResponseModel>> response) {
                if (response.isSuccessful() && response.code() == 200) {

                    ArrayList<MultiLanguageResponseModel> listMultiLangResponse = response.body();

                    if (listMultiLangResponse != null && !listMultiLangResponse.isEmpty()) {

                        ArrayList<SentensesEntaties> sentenceList = new ArrayList();

                        for (int i = 0; i < listMultiLangResponse.size(); i++) {
                            //   Log.e(TAG, "onResponse: "+listMultiLangResponse.get(i).toString() );
                            ArrayList<TranslationModel> multiLanguage = listMultiLangResponse.get(i).getTranslations();

                            SentensesEntaties sentenceRow = new SentensesEntaties();

                            for (int j = 0; j < multiLanguage.size(); j++) {
                                sentenceRow.setId(i);
                                if (j % 3 == 0) {
                                    sentenceRow.setEnglishText(multiLanguage.get(j).getText());
                                } else if (j % 2 == 0) {
                                    sentenceRow.setDesireText(multiLanguage.get(j).getText());
                                } else {
                                    sentenceRow.setNativeText(multiLanguage.get(j).getText());
                                }
                            }

                            sentenceList.add(sentenceRow);

                        }

                        viewModel.deleteSentences();
                        viewModel.insertAllSentences(sentenceList);

                        gotSentece = true;
                        checkNavigation();
                    }

                } else {
                    toast("onResponse" + response.toString());
                    try {
                        Log.e("TAG", "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MultiLanguageResponseModel>> call, Throwable t) {
                if (t.toString().contains("java.net.UnknownHostException")){
                    toast("Please Check Your Internet Connection");
                }else{
                    toast("Failure - " + t.toString());
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        LanguagePrefManager prefManager = new LanguagePrefManager(this);


        String nativeLangName = prefManager.getNativeLanguageName();
        String langToLearn = prefManager.getLanguageToLearnName();

        if (nativeLangName != null) {

            tvNativeLang.setText(nativeLangName);

        }
        if (langToLearn != null) {

            tvLangToLearn.setText(langToLearn);
        }
    }

    private void initViews() {

        animationView = findViewById(R.id.animation_view);
        chooseLearnLangBtn = findViewById(R.id.LB_linear_learn_lang);
        chooseNativeLangBtn = findViewById(R.id.LB_linear_native_lang);
        animation();
        startLearningLanguage = findViewById(R.id.LB_linear_start);

        tvNativeLang = findViewById(R.id.LB_tv_native_lang);
        tvLangToLearn = findViewById(R.id.LB_tv_learn_lang);

        viewModel = new ViewModelProvider(this).get(LearningViewModel.class);

        progressBar = findViewById(R.id.base_progress_bar);
    }

    private void animation() {
        // Custom animation speed or duration.
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
                    animationView.setProgress((Float) animation.getAnimatedValue());
                }
        );
        animator.start();
    }

    private void toast(String text) {
        Toast.makeText(BaseLearingActivity.this, text, Toast.LENGTH_SHORT).show();

    }

    private JsonArray getJsonBody(ArrayList<String> strings) {

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < strings.size(); i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("Text", strings.get(i));
            jsonArray.add(jsonObject);
        }

        return jsonArray;

    }

    void checkNavigation() {
        if (gotWords && gotSentece) {
            progressbar(false);
            Intent learingBaseActivity = new Intent(this, LearingMainActivity.class);
            learingBaseActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(learingBaseActivity);
        }
    }

    private void progressbar(Boolean isVisible) {
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}