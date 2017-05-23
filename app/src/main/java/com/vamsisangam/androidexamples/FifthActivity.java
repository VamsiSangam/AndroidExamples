package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FifthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        Log.d("today", "onCreate()");
    }

    public FifthActivity() {
        super();
        Log.d("today", "Constructor");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("today", "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("today", "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("today", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("today", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("today", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("today", "onDestroy()");
    }
}
