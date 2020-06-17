package com.test.worldborderapp.presenter;

import android.content.Context;

import com.test.worldborderapp.model.Country;
import com.test.worldborderapp.model.WorldData;

import java.util.ArrayList;

public class CountriesPresenter{

    private WorldData worldData;
    public CountriesPresenter(Context context){
        worldData = WorldData.getInstance(context);
    }

    public ArrayList<Country> getCountriesList() {
        return worldData.getList();
    }

    public void sortByName() {
        WorldData.sortByNameAbc();
    }

    public void sortByArea() {
        WorldData.sortByArea();
    }
}
