package com.example.daniel.w2d3_homework;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_FRAGMENT = "MainActivityFrgmentTAG_";

    Button muscPlyrBtn;
    Button dwnldBtn;

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muscPlyrBtn = (Button) findViewById(R.id.me_btnMscPlyr);
        dwnldBtn = (Button) findViewById(R.id.me_btnDnwld);

        manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.activity_main);

    }

    public void playMusic(View view) {
        MusicPlayerFragment music = new MusicPlayerFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.me_framLinear, music);
        transaction.commit();
    }

    public void downloadFile(View view) {
        DownloadFragment download = new DownloadFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.me_framLinear, download)
                .commit();

    }
}
