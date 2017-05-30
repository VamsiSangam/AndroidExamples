package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class TimerTickerReceiverActivity extends Activity {
    TimeTickReceiver r = new TimeTickReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_ticker_receiver);
    }

    public void registerReceiver(View v) {
        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(r, filter);
    }

    public void unregisterReceiver(View v) {
        unregisterReceiver(r);
    }
}
