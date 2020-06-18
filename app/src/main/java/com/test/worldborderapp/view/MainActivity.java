package com.test.worldborderapp.view;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.test.worldborderapp.R;
import com.test.worldborderapp.model.WorldData;
import com.test.worldborderapp.presenter.CountriesPresenter;

public class MainActivity extends AppCompatActivity {
    Fragment countriesFragment;
    ProgressDialog progressDialog;
    CountriesPresenter countriesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(WorldData.DATA_IS_READY);
        registerReceiver(CountriesReadyReceiver, intentFilter);
        Log.i("d", "onCreate: imri");
        countriesPresenter = new CountriesPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        progressDialog.setTitle("Loading all the world");
        progressDialog.show();
        progressDialog.setCancelable(false);

    }
    void startCountriesFragment(){
        countriesFragment = new CountriesFragment(countriesPresenter);
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
