package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends Activity {
    private final static int SECOND = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void showTodayActivity(View v) {
        Intent intent = new Intent(this, TodayActivity.class);

        startActivity(intent);
    }

    public void showSecondActivity(View v) {
        Intent intent = new Intent(this, SecondActivity.class);

        this.startActivityForResult(intent, SECOND);
    }

    public void showThirdActivity(View v) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }

    public void showFourthActivity(View v) {
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }

    public void showFifthActivity(View v) {
        Intent intent = new Intent(this, FifthActivity.class);
        startActivity(intent);
    }

    public void showDiscountCalculatorActivity(View v) {
        Intent intent = new Intent(this, DiscountCalculatorActivity.class);
        startActivity(intent);
    }

    public void showCourseFeeActivity(View v) {
        Intent intent = new Intent(this, CourseFeeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SECOND) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("name");
                Toast.makeText(this, "From SecondActivity - " + result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
