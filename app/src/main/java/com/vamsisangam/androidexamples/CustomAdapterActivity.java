package com.vamsisangam.androidexamples;

import android.app.ListActivity;
import android.os.Bundle;
import java.util.ArrayList;

/**
 * Created by Vamsi on 31-05-2017.
 */

public class CustomAdapterActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Company> companies = new ArrayList<>();

        companies.add(new Company("Microsoft", "0123456789", "www.microsoft.com", "microsoft"));
        companies.add(new Company("Apple", "1234567890", "www.apple.com", "apple"));
        companies.add(new Company("Facebook", "2345678901", "www.facebook.com", "facebook"));
        companies.add(new Company("Google", "4567890123", "www.google.com", "google"));

        CompanyAdapter adapter = new CompanyAdapter(this, companies);

        getListView().setAdapter(adapter);
    }
}
