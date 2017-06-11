package com.vamsisangam.androidexamples.networking.countries;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.vamsisangam.androidexamples.App.log;

public class CountriesActivity extends Activity {
    EditText countryName;
    ListView listCountries;
    ArrayList<Country> countries;

    // This RESTful service was free as of 11-June-2017, can be removed/replaced
    final static String ALL_COUNTRIES_URL = "https://restcountries.eu/rest/v2/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        init();

        countryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = countryName.getText().toString();

                if (input.length() > 2) {
                    ArrayList<Country> adapterData = new ArrayList<>();

                    for (Country country: countries) {
                        if (country.getName().toUpperCase().startsWith(input.toUpperCase())) {
                            log("Match - " + country.getName());
                            adapterData.add(country);
                        }
                    }

                    CountrySearchAdapter adapter = new CountrySearchAdapter(getApplicationContext(), adapterData);
                    listCountries.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
    }

    private void init() {
        countryName = (EditText) findViewById(R.id.countryName);
        listCountries = (ListView) findViewById(R.id.listCountries);
        countries = new ArrayList<>();
        loadAllCountryURLs();
    }

    private void loadAllCountryURLs() {
        (new Thread() {
            @Override
            public void run() {
                URL sourceUrl = null;
                InputStream is = null;
                BufferedReader br = null;

                try {
                    sourceUrl = new URL(ALL_COUNTRIES_URL);
                    is = sourceUrl.openStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder str = new StringBuilder();
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        str.append(line);
                    }

                    br.close();
                    is.close();

                    JSONArray array = new JSONArray(str.toString());

                    for (int i = 0; i < array.length(); ++i) {
                        countries.add(new Country(array.getJSONObject(i)));
                    }

                    countryName.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Data loaded! Ready to use application!", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch(Exception ex) {
                    log("Exception 1 - " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
