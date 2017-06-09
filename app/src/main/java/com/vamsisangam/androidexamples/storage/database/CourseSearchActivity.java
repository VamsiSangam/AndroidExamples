package com.vamsisangam.androidexamples.storage.database;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

import java.util.ArrayList;
import java.util.List;

public class CourseSearchActivity extends Activity {
    EditText courseName;
    ListView searchResults;
    ArrayList<String> resultIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_search);
        init();
    }

    private void init() {
        courseName = (EditText) findViewById(R.id.courseName);
        searchResults = (ListView) findViewById(R.id.searchResults);

        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CourseEditRemoveActivity.class);

                intent.putExtra("ID", resultIds.get(i));
                startActivity(intent);
                finish();
            }
        });
    }

    public void search(View v) {
        String name = courseName.getText().toString().toUpperCase();

        try {
            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(dbHelper.TABLE_COURSES, null, "UPPER(" + CourseDbHelper.COL_NAME + ") LIKE ?",
                        new String[]{"%" + name + "%"}, null, null, null);
            ArrayList<Course> courses = new ArrayList<>();

            while (cursor.moveToNext()) {
                courses.add(new Course(
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_ID)),
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_NAME)),
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_DURATION)),
                        cursor.getString(cursor.getColumnIndex(CourseDbHelper.COL_FEE))
                ));
            }

            CourseSearchResultAdapter adapter = new CourseSearchResultAdapter(this, courses);
            searchResults.setAdapter(adapter);
            Toast.makeText(this, courses.size()  + " result(s) found!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
