package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FourthActivity extends Activity {
    EditText number;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        init();
        if (savedInstanceState != null) {
            String savedOutput = savedInstanceState.getString("display");
            display.setText(savedOutput);
        }
    }

    private void init() {
        number = (EditText) findViewById(R.id.editText4Number);
        display = (TextView) findViewById(R.id.textView4Factors);
    }

    public void displayFactors(View v) {
        int n = Integer.parseInt(number.getText().toString());
        String output = "";

        for (int i = 1; i <= (n / 2)  + 1; ++i) {
            if (n % i == 0) {
                output += i + "\n";
            }
        }

        display.setText(output);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", display.getText().toString());
    }
}
