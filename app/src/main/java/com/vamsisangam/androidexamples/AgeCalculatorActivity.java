package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class AgeCalculatorActivity extends Activity {
    DatePicker dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculator);
        init();
    }

    private void init() {
        dp = (DatePicker) findViewById(R.id.ageCalculatorDate);
    }

    public void calculateAge(View v) {
        Calendar dob = Calendar.getInstance();

        dob.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());

        Log.d("Today", dob.toString());

        Calendar now = Calendar.getInstance();
        Log.d("Today", now.toString());
        Date birthday = dob.getTime();
        Date today = now.getTime();

        long diff = today.getTime() - birthday.getTime();
        long age = diff / (60l * 60l * 24l * 365l * 1000l);

        String result = "Your age is - " + age + " years";
        Toast.makeText(this.getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}
