package com.example.manveer.magicmirrorremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String fileName = "config.txt";
    EditText urlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlTextView = findViewById(R.id.UrlTextView);

        LaunchSite();
    }

    // reads the file and gets the url
    public String getUrlFromConfigFile(File configFile) {
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(configFile));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException ex) {
            Toast.makeText(this, "Reading file: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return text.toString();
    }


    // creates a config file with the url stored to get to the website
    public void OnSubmit(View v) {
        try {
            File configFile = new File(getApplicationContext().getFilesDir() + fileName);
            FileWriter writer = new FileWriter(configFile);

            writer.append(urlTextView.getText().toString());
            writer.flush();
            writer.close();
            Toast.makeText(this, "File saved, data: " + urlTextView.getText().toString(), Toast.LENGTH_LONG).show();

            LaunchSite();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void LaunchSite() {
        File configFile = new File(getApplicationContext().getFilesDir() + fileName);

        if(configFile.exists()) {
            Intent remoteActivity = new Intent(this, RemoteActivity.class);
            remoteActivity.putExtra("url", getUrlFromConfigFile(configFile));
            startActivity(remoteActivity);
        }
    }
}
