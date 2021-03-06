package com.example.daniel.w2d3_homework;


import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {

    public static final String TAG = "DownloadFragmentTAG_";

    String imageUrl = "https://i.ytimg.com/vi/AT2fmzPzsMg/maxresdefault.jpg";

    TextView dwnldTxtV;
    Button dnwldBtn;
    ImageView imgVw;

    public DownloadFragment() {
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
        Log.d(TAG, "onCreateView: ");

        return inflater.inflate(R.layout.fragment_download, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        dwnldTxtV = (TextView) getView().findViewById(R.id.fd_dwnld);
        dnwldBtn = (Button) getView().findViewById(R.id.fd_dwnldBtn);
        imgVw = (ImageView) getView().findViewById(R.id.fd_dwnldImage);
        dwnldTxtV.setText("Download File");
        
        dnwldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncDownload asyncDownload =  new AsyncDownload();
                asyncDownload.execute(imageUrl);
                Log.d(TAG, "onClick: ");
            }
        });

    }


}
