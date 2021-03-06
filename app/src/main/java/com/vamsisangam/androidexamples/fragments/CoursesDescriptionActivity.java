package com.vamsisangam.androidexamples.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;

import com.vamsisangam.androidexamples.R;

import static com.vamsisangam.androidexamples.App.log;

public class CoursesDescriptionActivity extends Activity {
    int mode;
    private static final int PORTRAIT = 1, LANDSCAPE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_description);



        if (getMode() == PORTRAIT) {
            String desc = getIntent().getStringExtra("description");
            TextView tv = (TextView) findViewById(R.id.txtCourseDescription);

            tv.setText(desc);
        } else {
            Intent intent = new Intent(this, CourseFragmentsActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private int getMode() {
        final int rotation = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getRotation();

        log("Rotation = " + rotation);

        switch (rotation) {
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                return LANDSCAPE;
        }

        return PORTRAIT;
    }
}
