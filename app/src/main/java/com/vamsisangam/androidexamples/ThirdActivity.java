package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends Activity {
    EditText number;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        init();
    }

    private void init() {
        number = (EditText) findViewById(R.id.editTextNumber);
        display = (TextView) findViewById(R.id.textViewFactors);
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
}
