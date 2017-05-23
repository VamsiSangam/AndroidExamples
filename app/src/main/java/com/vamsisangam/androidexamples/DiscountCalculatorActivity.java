package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DiscountCalculatorActivity extends Activity {
    EditText amount, rate;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_calculator);
        init();
    }

    public void init() {
        amount = (EditText) findViewById(R.id.discountCalcAmount);
        rate = (EditText) findViewById(R.id.discountCalcRate);
        result = (TextView) findViewById(R.id.discountCalcResult);
    }

    public void calculateDiscount(View v) {
        String amountString = amount.getText().toString();
        String rateString = rate.getText().toString();

        try {
            int a = Integer.parseInt(amountString);

            if (a < 0) {
                throw new IllegalArgumentException();
            }

            double d = Double.parseDouble(rateString);

            if (d < 0 || d > 100) {
                throw new IllegalArgumentException();
            }

            double amount = (a * d) / 100.0;

            result.setText("Result = Rs. " + amount);
        } catch (NumberFormatException ex) {
            result.setText("NumberFormatException occured - " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            result.setText("IllegalArgumentException occured, - " + ex.getMessage());
        } catch (Exception ex) {
            result.setText("Exception occured, - " + ex.getMessage());
        }
    }
}
