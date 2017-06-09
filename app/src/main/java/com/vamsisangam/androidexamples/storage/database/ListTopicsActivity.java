package com.vamsisangam.androidexamples.storage.database;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListTopicsActivity extends ListActivity {
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        course = getCourse(getIntent().getStringExtra("ID"));

        if (course == null) {
            Toast.makeText(this, "Invalid course selected!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        try {
            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(dbHelper.TABLE_TOPICS, null, CourseDbHelper.COL_COURSE + " = ?",
                    new String[]{course.getId()}, null, null, null);
            ArrayList<Topic> topics = new ArrayList<>();

            while (cursor.moveToNext()) {
                topics.add(new Topic(
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_COURSE)),
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_DURATION))
                ));
            }

            CourseTopicsListAdapter adapter = new CourseTopicsListAdapter(this, topics);

            getListView().setAdapter(adapter);
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Course getCourse(String courseId) {
        Course c = null;

        try {
            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(dbHelper.TABLE_COURSES, null, CourseDbHelper.COL_ID + " = ?",
                    new String[] {courseId}, null, null, null);
            String name, fee, duration;

            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_NAME));
                fee = cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_FEE));
                duration = cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_DURATION));

                c = new Course(courseId, name, duration, fee);
            } else {
                Toast.makeText(this, "Course not found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return c;
    }
}
