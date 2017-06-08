package com.vamsisangam.androidexamples.storage.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

public class CourseEditRemoveActivity extends Activity {
    EditText courseId, courseName, courseFee, courseDuration;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit_remove);
        init();

        if (getIntent() != null && getIntent().getStringExtra("ID") != null) {
            Intent intent = getIntent();
            Id = intent.getStringExtra("ID");
            courseId.setText(Id);
            queryCourseDetails(Id);
        }
    }

    private void init() {
        courseId = (EditText) findViewById(R.id.courseId);
        courseName = (EditText) findViewById(R.id.courseName);
        courseDuration = (EditText) findViewById(R.id.courseDuration);
        courseFee = (EditText) findViewById(R.id.courseFee);
    }

    public void getCourseDetails(View v) {
        Id = courseId.getText().toString();
        queryCourseDetails(Id);
    }

    private void queryCourseDetails(String courseId) {
        try {
            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(dbHelper.TABLE_NAME, null, CourseDbHelper.COL_ID + " = ?",
                    new String[] {courseId}, null, null, null);

            if (cursor.moveToFirst()) {
                courseName.setText(cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_NAME)));
                courseFee.setText(cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_FEE)));
                courseDuration.setText(cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_DURATION)));

                Toast.makeText(this, "Course details retrieved successfully!", Toast.LENGTH_LONG).show();
            } else {
                clearFields();
                Toast.makeText(this, "Course not found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clearFields() {
        courseName.setText("");
        courseFee.setText("");
        courseDuration.setText("");
    }

    public void saveCourseDetails(View v) {
        try {
            if (Id == null) {
                Id = courseId.getText().toString();
            }

            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            ContentValues values = new ContentValues();

            values.put(CourseDbHelper.COL_NAME, courseName.getText().toString());
            values.put(CourseDbHelper.COL_FEE, courseFee.getText().toString());
            values.put(CourseDbHelper.COL_DURATION, courseDuration.getText().toString());

            int count = db.update(CourseDbHelper.TABLE_NAME, values,
                    CourseDbHelper.COL_ID + " = ?", new String[]{Id});

            if (count == 1) {
                clearFields();
                courseId.setText("");
                Toast.makeText(this, "Course updated successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Course not found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            clearFields();
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void deleteCourse(View v) {
        ConfirmDeleteDialogFragment dialog = new ConfirmDeleteDialogFragment();

        dialog.show(getFragmentManager(), "Delete");
    }

    public void deleteCurrentlySelectedCourse() {
        try {
            if (Id == null) {
                Id = courseId.getText().toString();
            }

            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            int count = db.delete(CourseDbHelper.TABLE_NAME, CourseDbHelper.COL_ID + " = ?",
                    new String[]{Id});

            if (count == 1) {
                clearFields();
                courseId.setText("");
                Toast.makeText(this, "Course deleted successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Course not found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            clearFields();
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
