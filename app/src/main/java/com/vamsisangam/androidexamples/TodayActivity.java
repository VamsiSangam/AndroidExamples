package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class TodayActivity extends Activity {
    TextView textToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        init();
    }

    private void init() {
        textToday = (TextView) findViewById(R.id.textToday);
    }

    public void show(View v) {
        textToday.setText(new Date().toString());
    }

    public void clear(View v) {
        textToday.setText("");
    }
}
