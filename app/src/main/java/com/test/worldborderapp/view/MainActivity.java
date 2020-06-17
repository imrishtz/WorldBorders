package com.test.worldborderapp.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.test.worldborderapp.R;
import com.test.worldborderapp.model.WorldData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Fragment countriesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);
        //new JSONAsyncTask().execute("https://restcountries.eu/rest/v2/all");
        WorldData worldData = new WorldData();
        countriesFragment = new CountriesFragment(worldData);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, countriesFragment)
                .commit();
    }


}
