package com.test.worldborderapp.view;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
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
    ProgressDialog progressDialog;
    WorldData worldData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        progressDialog.setTitle("Loading all the world");
        progressDialog.show();
        progressDialog.setCancelable(false);
        IntentFilter intentFilter = new IntentFilter(WorldData.DATA_IS_READY);
        registerReceiver(CountriesReadyReceiver, intentFilter);
        worldData = new WorldData(this);
    }
    void startCountriesFragment(){
        countriesFragment = new CountriesFragment(worldData);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, countriesFragment)
                .commit();
    }
    BroadcastReceiver CountriesReadyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            progressDialog.dismiss();
            startCountriesFragment();
        }
    };
}
