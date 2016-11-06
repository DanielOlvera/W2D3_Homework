package com.example.daniel.w2d3_homework;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicPlayerFragment extends Fragment {

    public static final String TAG = "MusicPlayerFragmentTAG_";

    TextView muscTxtV;
    TextView songName;
    Button musicPlay;
    Button musicStop;

    MusicService musicService;

    boolean musicBound = false;


    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_music_player, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        muscTxtV = (TextView) getView().findViewById(R.id.f_mscPlyr);
        songName = (TextView) getView().findViewById(R.id.fm_songName);
        musicPlay = (Button) getView().findViewById(R.id.fm_muscPlay);
        musicStop = (Button) getView().findViewById(R.id.fm_muscStop);

        muscTxtV.setText("Music Player");

        musicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + musicPlay);
                musicService.playMusic();
            }
        });

        musicStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.stopMusic();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder musicBinder = (MusicService.MusicBinder) service;
            musicService = musicBinder.getService();
            musicBound = true;
            Log.d(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    public void doBindService(){
        getActivity().bindService(new Intent(getActivity(), MusicService.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE);
    }

    public void doUnbindService() {
        if (musicBound) {
            // Detach our existing connection.
            getActivity().unbindService(serviceConnection);
            musicBound = false;
        }
    }

}
