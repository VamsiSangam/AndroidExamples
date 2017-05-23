package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CourseFeeActivity extends Activity {
    Spinner course;
    RadioButton rbMorning, rbEvening;
    RadioGroup rbGroup;
    CheckBox oldStudent, material;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_fee);
        init();

        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculateCourseFee();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        rbMorning.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                calculateCourseFee();
            }
        });

        oldStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                calculateCourseFee();
            }
        });

        material.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                calculateCourseFee();
            }
        });
    }

    private void init() {
        course = (Spinner) findViewById(R.id.courseFeeCourseSpinner);
        rbMorning = (RadioButton) findViewById(R.id.courseFeeRbMorning);
        rbEvening = (RadioButton) findViewById(R.id.courseFeeRbEvening);
        rbGroup = (RadioGroup) findViewById(R.id.courseFeeRbGroup);
        oldStudent = (CheckBox) findViewById(R.id.courseFeeOldStudent);
        material = (CheckBox) findViewById(R.id.courseFeeCourseMaterial);
        result = (TextView) findViewById(R.id.courseFeeResult);
    }

    public void calculateCourseFee(View v) {
        calculateCourseFee();
    }

    private void calculateCourseFee() {
        double fee = 0, discount = 0;

        switch (course.getSelectedItemPosition()) {
            case 0:
                fee = 4000;
                break;
            case 1:
                fee = 4500;
                break;
            case 2:
                fee = 3700;
                break;
            case 3:
                fee = 4000;
                break;
            case 4:
                fee = 5000;
                break;
            case 5:
                fee = 4200;
                break;
        }

        if (rbMorning.isChecked()) {
            discount += 0.1;
        }

        if (oldStudent.isChecked()) {
            discount += 0.1;
        }

        if (material.isChecked()) {
            fee += 300;
        }

        fee = fee * (1 - discount);

        result.setText("Fee = Rs. " + fee);
    }
}
