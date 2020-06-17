package com.test.worldborderapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.worldborderapp.R;
import com.test.worldborderapp.model.Country;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BordersFragment extends Fragment  {

    TextView mNameTv;
    TextView mNativeNameTv;
    ListView mBorderingCountriesLv;


    String mName;
    String mNativeName;
    ArrayList<String> mBorderingCountries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.borders, container, false);
        mNameTv = view.findViewById(R.id.country_name);
        mNativeNameTv = view.findViewById(R.id.country_native_name);
        mBorderingCountriesLv = view.findViewById(R.id.list);
        mNameTv.setText(mName);
        mNativeNameTv.setText(mNativeName);
        Log.i("a", "onCreateView: imri"  + mBorderingCountries);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, mBorderingCountries);
        mBorderingCountriesLv.setAdapter(arrayAdapter);

        return view;
    }

    BordersFragment(String name, String nativeName, ArrayList<String> borderingCountries) {
        mName = name;
        mNativeName = nativeName;
        mBorderingCountries = borderingCountries;
    }
}
