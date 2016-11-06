package com.example.daniel.w2d3_homework;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Daniel on 11/6/16.
 */

public class AsyncDownload extends AsyncTask<String, Void, String>{

    private static final String TAG = "AsyncDownloadTAG_";

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        Log.d(TAG, "onPreExecute: ");
    }

    @Override
    protected String doInBackground(String... params) { //This is an array

        String urlImg = params[0];
        int fileLength = 0;
        try {

            StrictMode.ThreadPolicy strictMode = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(strictMode);

            URL url = new URL(urlImg);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            fileLength = urlConnection.getContentLength();

            File newFolder = new File("/res/drawable/downloadedImages");
            if (!newFolder.exists()){
                newFolder.mkdir();
            }

            File inputFile = new File(newFolder, "downloadedImage.jpg");
            InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
            byte[] data = new byte[1024];
            int total = 0;
            int count = 0;
            OutputStream outputStream = new FileOutputStream(inputFile);
            while ((count = inputStream.read(data)) != -1){
                total += count;
                outputStream.write(data, 0, count);
            }
            inputStream.close();
            outputStream.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "doInBackground: ");
        return "Download Complete...";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.d(TAG, "onProgressUpdate: ");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: ");

        String path = "/res/drawable/downloadedImages/downloadedImage.jpg";

        DownloadFragment downloadFragment = new DownloadFragment();
        downloadFragment.imgVw.setImageDrawable(Drawable.createFromPath(path));
    }
}

/*
Reference:
    https://www.youtube.com/watch?v=5Bo-ESPkpxI
 */