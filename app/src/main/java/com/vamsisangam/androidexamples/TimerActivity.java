package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TimerActivity extends Activity {
    ProgressBar bar;
    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        init();
    }

    private void init() {
        bar = (ProgressBar) findViewById(R.id.timerProgressBar);
        btn = (Button) findViewById(R.id.timerBtn);
        et = (EditText) findViewById(R.id.timerLimit);
    }

    public void startStopTimer(View v) {
        if (btn.getText().equals("Start")) {
            try {
                final int num = Integer.parseInt(et.getText().toString());

                bar.setProgress(0);
                btn.setText("Stop");
                bar.setMax(num);

                new Thread() {
                    @Override
                    public void run() {
                        for (int i = num; i >= 0; --i) {
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ex) {
                                Log.d("Today", "Error in TimerActivity");
                            }

                            bar.setProgress(num - i);
                        }

                        btn.post(new Runnable() {
                            @Override
                            public void run() {
                                btn.setText("Start");
                                bar.setProgress(0);
                            }
                        });
                    }
                }.start();
            } catch (NumberFormatException ex) {
                Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "An exception has occurred - " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            bar.setProgress(0);
            bar.setMax(0);
            btn.setText("Stop");
        }
    }
}
