package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {
    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        userInput = (EditText) findViewById(R.id.secondActEditText);
    }

    public void submitUserInput(View v) {
        Intent intent = getIntent();

        intent.putExtra("name", userInput.getText().toString());
        this.setResult(RESULT_OK, intent);
        finish();   // terminate the activity
    }
}
