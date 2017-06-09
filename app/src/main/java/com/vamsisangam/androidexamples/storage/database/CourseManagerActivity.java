package com.vamsisangam.androidexamples.storage.database;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;
import com.vamsisangam.androidexamples.storage.internal.*;

public class CourseManagerActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        readCourses();
    }

    private void readCourses() {
        try {
            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(dbHelper.TABLE_COURSES, null, null, null, null, null, null);
            String[] from = {dbHelper.COL_NAME, dbHelper.COL_FEE, dbHelper.COL_DURATION};
            int[] to = {R.id.courseName, R.id.courseFee, R.id.courseDuration};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.courses_list_item, cursor, from, to, 0);

            getListView().setAdapter(adapter);
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_db_manager_options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.coursesAddOption:
                Intent intent = new Intent(this, AddCourseActivity.class);

                startActivity(intent);
                break;
            case R.id.coursesEditOption:
                intent = new Intent(this, CourseEditRemoveActivity.class);

                startActivity(intent);
                break;
            case R.id.coursesSearchOption:
                intent = new Intent(this, CourseSearchActivity.class);

                startActivity(intent);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
