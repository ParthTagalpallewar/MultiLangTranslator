package com.android.translateapplication.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.translateapplication.R;
import com.android.translateapplication.data.responses.languagesResponse.LanguageResponse;
import com.android.translateapplication.data.retrofit.RetrofitClient;
import com.android.translateapplication.ui.FeatureSelectionActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.translateapplication.data.Constants.API_VERSION;
import static com.android.translateapplication.data.Constants.SCOPE;

public class SplashScreen extends AppCompatActivity {

    private int time = 2000;
    SplashViewModel viewModel;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        intiProperties();

        checkDataPresent();

    }

    private void checkDataPresent() {
        viewModel.getAllLanguages().observe(this, list -> {
            progressBar.setVisibility(View.VISIBLE);
            if (list.size() > 0) {
                showBasicSplash();
            } else {
                fetchData();
            }
        });

    }

    private void fetchData() {
        Call<LanguageResponse> responseCall = RetrofitClient.getInstance().getMyApi().getLanguages(API_VERSION, SCOPE);

        responseCall.enqueue(new Callback<LanguageResponse>() {
            @Override
            public void onResponse(Call<LanguageResponse> call, Response<LanguageResponse> response) {

                progressBar.setVisibility(View.INVISIBLE);

                if (response.isSuccessful() && response.code() == 200) {

                    LanguageResponse languageResponse = response.body();
                    viewModel.insertAllLanguages(languageResponse.getTranslation().getLanguages());

                    move();
                } else {

                    Toast.makeText(SplashScreen.this, "onResponse" + response.toString(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LanguageResponse> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.e("error ", t.toString());Log.e("error ", t.toString());

                String internetError = getString(R.string.internetError);
                if (t.toString().contains("java.net.UnknownHostException")){
                    Toast.makeText(SplashScreen.this, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SplashScreen.this, "Failure - " + t.toString().toString(), Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void intiProperties() {
        viewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        progressBar = findViewById(R.id.splash_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void showBasicSplash() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        move();
                        finish();
                    }
                }
            }
        };
        // start thread
        thread.start();
    }

    private void move() {
        progressBar.setVisibility(View.INVISIBLE);
        Log.e("SplashMoveTrigger", "move: ");
        startActivity(new Intent(getBaseContext(), FeatureSelectionActivity.class));
    }
}