package com.android.translateapplication.ui.translation;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.translateapplication.R;
import com.android.translateapplication.data.pref.PrefProvider;
import com.android.translateapplication.data.responses.translationResposne.TranslationModel;
import com.android.translateapplication.data.responses.translationResposne.TranslationResponse;
import com.android.translateapplication.data.retrofit.RetrofitClient;
import com.android.translateapplication.ui.translation.languages.SelectLanguageTo;
import com.android.translateapplication.ui.translation.languagesfrom.SelectLanguageFrom;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.translateapplication.data.Constants.API_VERSION;

public class TraslationActivity extends AppCompatActivity {

    private static final String TAG = "TraslationActivity";
    private static final int REQUEST_CODE_SPEECH_INPUT = 99;
    TextToSpeech textToSpeech;

    private EditText unTranslatedText;
    private TextView translatedText;

    private LinearLayout btnTranslateText, btnTranslateVoice;
    private LinearLayout linearTranslationTo, linearTranslationFrom;


    private TextView textViewFrom, textViewTo;

    private LinearLayout copyLayout, speakLayout, shareLayout, clearLayout;
    private ProgressBar progressBar;
    private CardView interchangeLanguages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslation);

        initViews();

        selectLanguageClicked();

        btnTranslateText.setOnClickListener(v -> translateBtnClicked());

        voiceTranslate();

        handelBottomClicks();

        interchangeLanguages.setOnClickListener(v -> {
            intechageLanguage();
        });


    }

    private void intechageLanguage() {


        PrefProvider pref = new PrefProvider(this);
        String languageTo = pref.getTRANSLATE_TO();
        String languageFrom = pref.getTRANSLATE_FROM();
        String languageToName = pref.getTRANSLATE_TO_NaME();
        String languageFromName = pref.getTRANSLATE_FROM_Name();

        if ((languageFromName != null) && (languageToName != null)) {
            pref.saveTranslateFromName(languageTo, languageToName);
            pref.saveTranslateToName(languageFrom, languageFromName);

            textViewFrom.setText(languageToName);
            textViewTo.setText(languageFromName);
        }else{
            toast("Please Choose Both Language first");
        }


    }


    private void initViews() {
        textViewTo = findViewById(R.id.trans_tv_to);
        textViewFrom = findViewById(R.id.trans_tv_from);

        linearTranslationTo = findViewById(R.id.trans_linear_to);
        linearTranslationFrom = findViewById(R.id.trans_linear_from);

        btnTranslateText = findViewById(R.id.translation_linear_translate);
        btnTranslateVoice = findViewById(R.id.translation_linear_voice);

        unTranslatedText = findViewById(R.id.translate_et_textToConvert);
        translatedText = findViewById(R.id.translation_tv_showText);

        progressBar = findViewById(R.id.translation_progressBar);

        copyLayout = findViewById(R.id.linear_copy);
        shareLayout = findViewById(R.id.linear_share);
        clearLayout = findViewById(R.id.linear_clear);
        speakLayout = findViewById(R.id.linear_speak);

        interchangeLanguages = findViewById(R.id.interchangeLanguage);


        // create an object textToSpeech and adding features into it
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
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
    protected void onStart() {
        super.onStart();
        PrefProvider pref = new PrefProvider(this);
        String text = pref.getTextToSearch();

        String fromName = pref.getTRANSLATE_FROM_Name();
        String toName = pref.getTRANSLATE_TO_NaME();


        if (text != null) {
            unTranslatedText.setText(text);
        }
        if (fromName != null) {
            textViewFrom.setText(fromName);
        }
        if (toName != null) {
            textViewTo.setText(toName);
        }


    }

    private void selectLanguageClicked() {

        linearTranslationTo.setOnClickListener(v -> {

            //saving text if not null
            String text = unTranslatedText.getText().toString();
            if (!TextUtils.isEmpty(text)) {
                new PrefProvider(this).saveTextToSearch(text);
            }

            Intent selectLangIntent = new Intent(this, SelectLanguageTo.class);
            startActivity(selectLangIntent);
        });

        linearTranslationFrom.setOnClickListener(v -> {
            //saving text if not null
            String text = unTranslatedText.getText().toString();
            if (!TextUtils.isEmpty(text)) {
                new PrefProvider(this).saveTextToSearch(text);
            }


            Intent selectLangIntent = new Intent(this, SelectLanguageFrom.class);
            startActivity(selectLangIntent);
        });
    }

    private void voiceTranslate() {
        btnTranslateVoice.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            } catch (Exception e) {
                toast(e.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                unTranslatedText.setText(Objects.requireNonNull(result).get(0));
                translateBtnClicked();
            }
        }
    }

    private void translateBtnClicked() {
        String stringTextToConvert = unTranslatedText.getText().toString();
        //Getting Both Languages from Pref
        PrefProvider prefProvider = new PrefProvider(this);
        String fromLangCode = prefProvider.getTRANSLATE_FROM();
        String toLangCode = prefProvider.getTRANSLATE_TO();


        if (TextUtils.isEmpty(stringTextToConvert)) {
            toast("Please Enter Valid Text To Convert");
        } else if (toLangCode == null) {
            toast("Please Choose Language To ");
        } else if (fromLangCode == null) {
            toast("Please Choose Language From");
        } else {
            new PrefProvider(this).saveTextToSearch(stringTextToConvert);
            JsonArray bodyToSend = getJsonBody(stringTextToConvert);
            makeRequestCall(bodyToSend, fromLangCode, toLangCode);
        }


    }

    private void makeRequestCall(JsonArray bodyToSend, String fromLangCode, String toLangCode) {

        progressbar(true);


        Call<ArrayList<TranslationResponse>> responseCall = RetrofitClient.getInstance().getMyApi().getTranslation(API_VERSION, toLangCode, fromLangCode, bodyToSend);

        responseCall.enqueue(new Callback<ArrayList<TranslationResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TranslationResponse>> call, Response<ArrayList<TranslationResponse>> response) {

                progressbar(false);

                if (response.isSuccessful() && response.code() == 200) {

                    ArrayList<TranslationResponse> listOfLanguages = response.body();
                    if (!listOfLanguages.isEmpty()) {
                        ArrayList<TranslationModel> translationArray = listOfLanguages.get(0).getTranslations();
                        if (!translationArray.isEmpty()) {
                            String text = translationArray.get(0).getText();
                            translatedText.setText(text);
                        }
                    }else{
                        toast("Could Not Get This response try searching else");
                    }

                } else {


                    try {
                        toast("Error -  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<TranslationResponse>> call, Throwable t) {

                progressbar(false);

                Log.e("error ", t.toString());Log.e("error ", t.toString());

                String internetError = getString(R.string.internetError);
                if (t.toString().contains("java.net.UnknownHostException")){
                    toast("Please Check Your Internet Connection");
                }else{
                    toast("Failure - " + t.toString());
                }




            }
        });


    }

    private void toast(String text) {
        Toast.makeText(TraslationActivity.this, text, Toast.LENGTH_SHORT).show();

    }

    private JsonArray getJsonBody(String stringTextToConvert) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Text", stringTextToConvert);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);

        return jsonArray;

    }

    private void progressbar(Boolean isVisible) {
        if (isVisible) progressBar.setVisibility(View.VISIBLE);
        else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    void handelBottomClicks() {

        copyLayout.setOnClickListener(v -> {
            String text = translatedText.getText().toString();
            if (TextUtils.isEmpty(text)) {
                toast("Please Translate Text First");
            } else {
                ClipboardManager clipboard = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", text);
                    clipboard.setPrimaryClip(clip);

                    toast("text Copied");
                } else {
                    toast("Your Device is not Compatible to perform this operation");
                }

            }
        });

        speakLayout.setOnClickListener(v -> {
            String text = translatedText.getText().toString();
            if (TextUtils.isEmpty(text)) {
                toast("Please Translate Text First");
            } else {
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        shareLayout.setOnClickListener(v -> {

            String text = translatedText.getText().toString();
            if (TextUtils.isEmpty(text)) {
                toast("Please Translate Text First");
            } else {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }


        });

        clearLayout.setOnClickListener(v -> {
            unTranslatedText.setText(null);
            translatedText.setText(null);
            new PrefProvider(this).saveTextToSearch(null);
        });
    }

}
