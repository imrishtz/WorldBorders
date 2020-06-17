package com.test.worldborderapp.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.test.worldborderapp.util.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class WorldData {
    private JSONArray jArray;
    private static ArrayList<Country> countries;
    private static Map<String, Integer> alpha3ToIndex;
    public static final String DATA_IS_READY = "com.test.worldborderapp.model.DATA_IS_READY";
    private static WorldData worldDataInstance;
    public static WorldData getInstance(Context context) {
        if (worldDataInstance == null) {
            worldDataInstance = new WorldData(context);
        }
        return worldDataInstance;
    }

    public ArrayList<Country> getList() {
        return  countries;
    }
    static public ArrayList<String> getBorderingCountries(int index) {
        ArrayList<String> ret = new ArrayList<>();
        Country country = countries.get(index);
        ArrayList<String> bordering = country.getBorderingCountriesAlpha3();
        for (int i = 0; i < bordering.size(); ++i){
            ret.add(getNameFromAlpha3Code(bordering.get(i)));
        }
        return ret;
    }
    static private String getNameFromAlpha3Code(String alpha3Code) {
        int index = alpha3ToIndex.get(alpha3Code);
         return countries.get(index).getName();
    }
    private WorldData(Context context) {
        String data = FetchData.fetch();
        try {
            jArray = new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        countries = new ArrayList<>();
        alpha3ToIndex = new HashMap<>();

        for (int i = 0; i < jArray.length(); ++i) {
            JSONObject country = null;
            String name = "", nativeName = "", alpha3Code = "";
            int area = 0;
            ArrayList<String> tempBordersCountries = null;
            try {
                country = (JSONObject) jArray.get(i);
                name = country.getString("name");
                nativeName = country.getString("nativeName");
                alpha3Code = country.getString("alpha3Code");
                JSONArray bordersJSON = country.getJSONArray("borders");
                tempBordersCountries = new ArrayList<>(bordersJSON.length());
                for (int j = 0; j < bordersJSON.length(); ++j) {
                    tempBordersCountries.add(bordersJSON.getString(j));
                }
                alpha3ToIndex.put(alpha3Code, i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                area = country.getInt("area");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            countries.add(new Country(name, nativeName,alpha3Code, tempBordersCountries, area));
        }
        Intent intent = new Intent(DATA_IS_READY);
        context.sendBroadcast(intent);
    }

    public static void sortByArea( ) {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country lhs, Country rhs) {
                return lhs.getArea() > rhs.getArea() ? -1 : 1;
            }
        });
        int index = 0;
        for (Country country :countries) {
            alpha3ToIndex.put(country.getAlpha3code(), index++);
        }
    }
    public static void sortByNameAbc( ) {
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country lhs, Country rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        int index = 0;
        for (Country country :countries) {
            alpha3ToIndex.put(country.getAlpha3code(), index++);
        }
    }
}
