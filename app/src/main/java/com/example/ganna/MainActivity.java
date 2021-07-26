package com.example.ganna;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.ClientCertRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.monstertechno.adblocker.AdBlockerWebView;
import com.monstertechno.adblocker.util.AdBlocker;

public class MainActivity extends AppCompatActivity {
    private WebView mwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mwebView = findViewById(R.id.webview);
        WebSettings webSettings = mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);

        new AdBlockerWebView.init(this).initializeWebView(mwebView);
        mwebView.setWebViewClient(new Browser_home());
        mwebView.loadUrl("https://gaana.com/");


    }

    private class Browser_home extends WebViewClient {
        Browser_home() {
        }

        @SuppressWarnings("deprecation")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

            return AdBlockerWebView.blockAds(view, url) ? AdBlocker.createEmptyResource() :
                    super.shouldInterceptRequest(view, url);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}