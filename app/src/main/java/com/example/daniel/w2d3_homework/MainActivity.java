package com.example.daniel.w2d3_homework;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.StrictMode;
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

    private MusicService musicService;

    boolean musicBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        muscPlyrBtn = (Button) findViewById(R.id.me_btnMscPlyr);
        dwnldBtn = (Button) findViewById(R.id.me_btnDnwld);

        manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.activity_main);

        doBindService();

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MusicBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

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

    public void doBindService(){
        bindService(new Intent(MainActivity.this, MusicService.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public void doUnbindService(){
        if(musicBind){
            unbindService(serviceConnection);
            musicBind = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }
}
/*
Theoretical tasks:
    What databases do you use in Android?
        - SQLite 3 has a full support in Android, however you could use other DB by connecting with a web service,
        and to the data mapping with an ORM like Sugar, GreenDAO or Realm
    What is a Looper??
        - Is a class which is used to execut multiple messages (Runnables) in a queue. Is someone wants
        to execute multiple messages. There are two ways to start a Thread: by implementing Runnable or by
        extending Thread.
    What is a Handler?
        - Allows you to send and process messages and runnable objects assocciated with ThreadsMessageQueue
 */
/*
Reference:
    https://developer.android.com/reference/android/os/AsyncTask.html
    http://stackoverflow.com/questions/3028306/download-a-file-with-android-and-showing-the-progress-in-a-progressdialog
 */