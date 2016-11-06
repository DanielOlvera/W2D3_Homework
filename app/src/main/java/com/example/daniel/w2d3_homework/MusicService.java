package com.example.daniel.w2d3_homework;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener{

    private static final String TAG = "MusicServiceTAG_";

    private MediaPlayer musicPlayer = null;

    private final IBinder musicBinder = new MusicBinder();

    public MusicService() {
    }

    public class MusicBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: ");
        return musicBinder;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        musicPlayer = MediaPlayer.create(this, R.raw.lipsofanangel);
        musicPlayer.setOnErrorListener(this);

        if (musicPlayer != null){
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(100, 100);
        }

        musicPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                onError(musicPlayer, what, extra);
                return true;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (musicPlayer != null) {
            try{
                musicPlayer.stop();
                musicPlayer.reset();
                musicPlayer.release();
            }finally {
                musicPlayer = null;
            }
        }
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (musicPlayer != null) {
            try{
                musicPlayer.stop();
                musicPlayer.reset();
                musicPlayer.release();
            }finally {
                musicPlayer = null;
            }
        }
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(TAG, "onPrepared: ");
    }

    public void playMusic(){
        musicPlayer.start();
        Log.d(TAG, "playMusic: ");
    }

    public void stopMusic(){
        musicPlayer.stop();
        musicPlayer.reset();
        musicPlayer.release();
        Log.d(TAG, "stopMusic: ");
    }
}
