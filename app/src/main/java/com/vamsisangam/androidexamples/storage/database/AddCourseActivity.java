package com.vamsisangam.androidexamples.storage.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.vamsisangam.androidexamples.App.*;
import com.vamsisangam.androidexamples.R;

public class AddCourseActivity extends Activity {
    EditText courseName, courseFee, courseDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_row);
        init();
    }

    private void init() {
        courseName = (EditText) findViewById(R.id.courseName);
        courseFee = (EditText) findViewById(R.id.courseFee);
        courseDuration = (EditText) findViewById(R.id.courseDuration);
    }

    public void addCourse(View v) {
        try {
            String name = courseName.getText().toString();
            int fee = Integer.parseInt(courseFee.getText().toString());
            int duration = Integer.parseInt(courseDuration.getText().toString());
            CourseDbHelper courseDb = new CourseDbHelper(this);
            SQLiteDatabase db = courseDb.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(CourseDbHelper.COL_NAME, name);
            values.put(CourseDbHelper.COL_FEE, fee);
            values.put(CourseDbHelper.COL_DURATION, duration);

            db.insert(CourseDbHelper.TABLE_NAME, null, values);

            Toast.makeText(this, "Course added to database successfully!", Toast.LENGTH_LONG).show();
            courseName.setText("");
            courseDuration.setText("");
            courseFee.setText("");
        } catch (Exception ex) {
            error(ex.getMessage());
        }
    }
}
