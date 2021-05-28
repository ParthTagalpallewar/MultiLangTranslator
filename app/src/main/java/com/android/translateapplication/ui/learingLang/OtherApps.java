package com.android.translateapplication.ui.learingLang;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.translateapplication.R;

public class OtherApps extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other__apps);

        WebView webview = findViewById(R.id.webView);

        try {
            webview.loadUrl("https://play.google.com/store/apps/dev?id=5879196017425043903");
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
