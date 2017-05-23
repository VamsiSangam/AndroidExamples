package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BirthdayCalculatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_calculator);
    }

    private void init() {

    }

    public void inputDOB(View v) {
        DatePickerDialogFragment dialog = new DatePickerDialogFragment();

        dialog.show(getFragmentManager(), "Tag");
    }
}
