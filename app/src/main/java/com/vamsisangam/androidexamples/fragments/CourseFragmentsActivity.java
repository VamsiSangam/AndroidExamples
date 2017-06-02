package com.vamsisangam.androidexamples.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Surface;
import android.view.WindowManager;
import static com.vamsisangam.androidexamples.App.*;
import com.vamsisangam.androidexamples.R;

public class CourseFragmentsActivity extends Activity {
    int mode;
    private static final int PORTRAIT = 1, LANDSCAPE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_fragments);

        mode = getMode();
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

    public void setCourseDescription(String description) {
        if (mode == PORTRAIT) {
            Intent intent = new Intent(this, CoursesDescriptionActivity.class);

            intent.putExtra("description", description);
            startActivity(intent);
        } else {
            CourseDescriptionFragment f = (CourseDescriptionFragment) getFragmentManager()
                    .findFragmentById(R.id.courseDescriptionFragment);

            f.setDescription(description);
        }
    }
}
