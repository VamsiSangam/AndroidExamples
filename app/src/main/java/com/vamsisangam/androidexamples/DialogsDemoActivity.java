package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DialogsDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs_demo);
    }

    public void showAlertDialog(View v) {
        AlertDialogFragment alertDialog = new AlertDialogFragment();

        // 2nd parameter is a tag, can be anything
        alertDialog.show(getFragmentManager(), "quit");
    }

    public void showDatePickerDialog(View v) {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();

        dialog.show(getFragmentManager(), "date");
    }

    public void showSpinnerProgressDialog(View v) {
        final ProgressDialog dialog = ProgressDialog.show(this, "Loading", "Loading please wait...");

        (new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    Log.d("Today", "Exception in showHorizontalProgressDialog");
                }

                dialog.dismiss();
                // causes error
                // Toast.makeText(getApplicationContext(), "Done loading!", Toast.LENGTH_LONG).show();
            }
        }).start();
    }

    public void showHorizontalProgressDialog(View v) {
        final ProgressDialog dialog = new ProgressDialog(this);

        dialog.setMax(20);
        dialog.setTitle("Processing");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

        (new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 90; ++i) {
                        double multiplier = Math.sin(Math.toRadians(i));
                        Log.d("Today", "i = " + i + ", mul = " + multiplier);
                        Thread.sleep((long) (100 * multiplier));
                        dialog.setProgress(Math.min(i + 10, 100));
                    }
                } catch (Exception ex) {
                    Log.d("Today", "Exception in showHorizontalProgressDialog");
                }

                dialog.dismiss();
                // Toast.makeText(getApplicationContext(), "Done processing!", Toast.LENGTH_LONG).show();
            }
        }).start();
    }
}
