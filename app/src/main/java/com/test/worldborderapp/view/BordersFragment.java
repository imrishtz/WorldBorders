package com.test.worldborderapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.worldborderapp.R;

import java.util.ArrayList;

public class BordersFragment extends Fragment  {

    private String mName;
    private String mNativeName;
    private ArrayList<String> mBorderingCountries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.borders, container, false);
        TextView mNameTv = view.findViewById(R.id.country_name);
        TextView mNativeNameTv = view.findViewById(R.id.country_native_name);
        ListView mBorderingCountriesLv = view.findViewById(R.id.list);
        mNameTv.setText(mName);
        mNativeNameTv.setText(mNativeName);
        Log.i("a", "onCreateView: imri"  + mBorderingCountries);
        if (mBorderingCountries.isEmpty()) {
            TextView noBorders = view.findViewById(R.id.borders_header);
            noBorders.setText("This country have no bordering countries");
            noBorders.setGravity(Gravity.CENTER);
        } else {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, mBorderingCountries);
            mBorderingCountriesLv.setAdapter(arrayAdapter);
        }

        return view;
    }

    BordersFragment(String name, String nativeName, ArrayList<String> borderingCountries) {
        mName = name;
        mNativeName = nativeName;
        mBorderingCountries = borderingCountries;
    }
}
