package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class BirthdayCalculatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_calculator);
    }

    private void init() {

    }

    public void inputDOB(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this, new HandleDateSet(), 0, 1, 2017);

        dialog.show();
    }

    class HandleDateSet implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar dob = Calendar.getInstance();

            dob.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

            Calendar now = Calendar.getInstance();
            long diff = 0;

            do {
                Date birthday = dob.getTime();
                Date today = now.getTime();
                diff = today.getTime() - birthday.getTime();

                dob.add(Calendar.YEAR, 1);
            } while (diff > 0);

            long days = -diff / (60l * 60l * 24l * 1000l);

            Toast.makeText(getApplicationContext(), days + " days are left for your birthday!", Toast.LENGTH_LONG).show();
        }
    }
}
