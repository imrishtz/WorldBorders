package com.test.worldborderapp.model;

import java.util.ArrayList;

public class Country {

    private String alpha3code;
    private String name;
    private String nativeName;
    private ArrayList<String> borderingCountriesAlpha3;
    private int area;
    Country(String name, String nativeName, String alpha3code, ArrayList<String> borderingCountriesAlpha3, int area) {
        this.name = name;
        this.nativeName = nativeName;
        this.alpha3code = alpha3code;
        this.borderingCountriesAlpha3 = borderingCountriesAlpha3;
        this.area = area;
    }
    public String getName() {
        return name;
    }
    public String getNativeName() {
        return nativeName;
    }

    public String getAlpha3code() {
        return alpha3code;
    }

    public ArrayList<String> getBorderingCountriesAlpha3() {
        return borderingCountriesAlpha3;
    }

    public int getArea() {
        return area;
    }
}
