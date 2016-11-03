package com.example.daniel.w2d3_homework;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicPlayerFragment extends Fragment {

    public static final String TAG = "MusicPlayerFragmentTAG_";

    TextView muscTxtV;
    TextView songName;
    Button musicPlay;
    Button musicStop;


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
    }
}
