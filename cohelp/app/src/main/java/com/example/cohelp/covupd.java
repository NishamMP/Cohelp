package com.example.cohelp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class covupd extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("COVID STATISTICS");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#018786")));
        setContentView(R.layout.activity_covupd);

        webView =(WebView) findViewById(R.id.web_covup);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.covid19india.org");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}