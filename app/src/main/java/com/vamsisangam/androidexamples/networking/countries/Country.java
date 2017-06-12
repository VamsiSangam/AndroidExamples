package com.vamsisangam.androidexamples.networking.countries;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.vamsisangam.androidexamples.App.log;

/**
 * Created by Vamsi on 11-06-2017.
 */

public class Country {
    private String name, capital, flagURL, alpha2Code, alpha3Code;
    private long population;
    private double lat, lng;
    private ArrayList<String> languages;

    public Country(JSONObject json) {
        try {
            languages = new ArrayList<>();
            name = json.getString("name");
            alpha2Code = json.getString("alpha2Code");
            alpha3Code = json.getString("alpha3Code");
            capital = json.getString("capital");

            // using another RESTful serive only for the flag because the other one
            // provides a flag image in SVG format which is not supported by android
            flagURL = "http://www.geognos.com/api/en/countries/flag/" + alpha2Code + ".png";
            population = json.getLong("population");

            JSONArray arr = json.getJSONArray("latlng");

            if (arr.length() == 2) {
                lat = Double.parseDouble(arr.get(0).toString());
                lng = Double.parseDouble(arr.get(1).toString());
            }

            arr = json.getJSONArray("languages");

            for (int i = 0; i < arr.length(); ++i) {
                JSONObject language = arr.getJSONObject(i);

                languages.add(language.getString("name"));
            }
        } catch (Exception ex) {
            log("Exception - " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public long getPopulation() {
        return population;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public ArrayList<String> getLanguages() {
        return new ArrayList<>(languages);
    }

    public String getDetailedInfo() {
        StringBuilder info = new StringBuilder();

        info.append("Aplha 2 Code - " + alpha2Code + "\n");
        info.append("Aplha 3 Code - " + alpha3Code + "\n");
        info.append("Country population - " + population + "\n");
        info.append("Latitude - " + lat + "\n");
        info.append("Longitude - " + lng + "\n");
        info.append("Languages spoken - ");

        for (int i = 0; i < languages.size() - 1; ++i) {
            info.append(languages.get(i) + ", ");
        }

        info.append(languages.get(languages.size() - 1));

        return info.toString();
    }
}
