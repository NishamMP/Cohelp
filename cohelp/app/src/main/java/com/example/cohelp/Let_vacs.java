package com.example.cohelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Let_vacs extends AppCompatActivity {
    ProgressDialog Dialog;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("LET'S VACCINATED");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#018786")));
        setContentView(R.layout.activity_let_vacs);
        Dialog= new ProgressDialog(this);
        Dialog.setMessage("Loading");
        Dialog.show();
        webView =(WebView) findViewById(R.id.web_vacs);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.cowin.gov.in");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}