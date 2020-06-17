package com.test.worldborderapp.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class FetchData {

    private static final String TAG = "FetchData";

    public static String fetch(){
        String[] params = {"https://restcountries.eu/rest/v2/all", ""};
        try {
            new JSONAsyncTask().execute(params).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return params[1];
    }

    static class JSONAsyncTask extends AsyncTask<String,StringBuffer,StringBuffer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected StringBuffer doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                InputStreamReader isReader = new InputStreamReader(in);
                //Creating a BufferedReader object
                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer sb = new StringBuffer();
                String str;
                while((str = reader.readLine())!= null){
                    sb.append(str);
                }
                params[1] = sb.toString();
                return sb;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  null;
        }
        @Override
        protected void onPostExecute(StringBuffer sb) {
            if (sb == null) {
                Log.i(TAG, "onPostExecute: null" );
            } else {
                Log.i(TAG, "onPostExecute: " + sb.toString());
            }
        }
    }
}
