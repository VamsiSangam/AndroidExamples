package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import static com.vamsisangam.androidexamples.App.*;

public class SumBoundServiceActivity extends Activity {
    EditText number;
    TextView result;
    boolean bound;
    SumBoundService sumService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_bound_service);
        init();
    }

    private void init() {
        number = (EditText) findViewById(R.id.SumBoundServiceNumber);
        result = (TextView) findViewById(R.id.SumBoundServiceResult);
        bound = false;
    }

    @Override
    protected void onStart() {
        log("onStart() - " + this.getClass().getName());
        super.onStart();
    }

    @Override
    protected void onStop() {
        log("onStop() - " + this.getClass().getName());
        super.onStop();
    }

    private void connectToService() {
        Intent intent = new Intent(this, SumBoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void bind(View v) {
        log("bind() - Activity");

        if (!bound) {
            connectToService();
            bound = true;
            log("Connection done");
        }
    }

    public void unbind(View v) {
        log("unbind() - Activity");

        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    public void getSum(View v) {
        int num = Integer.parseInt(number.getText().toString());
        int sum = sumService.getSum(num);

        result.setText("Result = " + sum);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            log("onServiceConnected() - " + this.getClass().getName());
            bound = true;
            SumBoundService.ResultBinder binder = (SumBoundService.ResultBinder) iBinder;
            sumService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            log("onServiceDisconnected() - " + this.getClass().getName());
            bound = false;
        }
    };
}
