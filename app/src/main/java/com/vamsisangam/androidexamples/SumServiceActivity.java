package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SumServiceActivity extends Activity {
    EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_service);
        init();
    }

    private void init() {
        number = (EditText) findViewById(R.id.SumServiceNumber);
    }

    public void calculateSumUsingService(View v) {
        Intent intent = new Intent(this, SumService.class);

        intent.putExtra("number", Integer.parseInt(number.getText().toString()));
        startService(intent);
    }

    public void calculateSumUsingIntentService(View v) {
        Intent intent = new Intent(this, SumIntentService.class);

        intent.putExtra("number", Integer.parseInt(number.getText().toString()));
        startService(intent);
    }
}
