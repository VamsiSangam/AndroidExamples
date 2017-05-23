package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toast);
    }

    public void showCustomToast(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toast_white_image_toast,
                (ViewGroup) findViewById(R.id.customToastLinearLayout));

        ImageView img = (ImageView) layout.findViewById(R.id.customToastImage);
        img.setImageResource(R.drawable.bell);

        TextView tv = (TextView) layout.findViewById(R.id.customToastText);
        tv.setText("Hello there!");

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
