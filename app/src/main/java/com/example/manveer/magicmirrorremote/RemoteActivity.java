package com.example.manveer.magicmirrorremote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class RemoteActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_remote);

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        setContentView(webView);

        String url = getIntent().getExtras().getString("url");
        Toast.makeText(this, "url to load: " + url, Toast.LENGTH_LONG).show();
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
