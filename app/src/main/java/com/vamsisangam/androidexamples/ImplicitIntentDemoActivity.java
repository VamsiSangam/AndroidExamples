package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ImplicitIntentDemoActivity extends Activity {
    EditText action, category, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_demo);
        init();
    }

    private void init() {
        action = (EditText) findViewById(R.id.ImplicitIntentAction);
        category = (EditText) findViewById(R.id.ImplicitIntentCategory);
        data = (EditText) findViewById(R.id.ImplicitIntentData);
    }

    public void invokeImplicitIntent(View v) {
        Intent intent = new Intent();

        intent.setAction(action.getText().toString());
        startActivity(intent);
    }
}
