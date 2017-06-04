package com.vamsisangam.androidexamples.storage.internal;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SimpleAdapter;
import com.vamsisangam.androidexamples.R;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static com.vamsisangam.androidexamples.App.*;

public class CourseManagerActivity extends ListActivity {
    public static final String FILE_NAME = "courses.txt";
    private ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ArrayList<HashMap<String, String>> getDataForAdapter() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        for (Course course : courses) {
            data.add(course.getMap());
        }

        return data;
    }

    @Override
    protected void onStart() {
        super.onStart();
        readCourses();

        SimpleAdapter adapter = new SimpleAdapter(this, getDataForAdapter(), R.layout.courses_list_item,
                new String[]{ "name", "duration", "fee" },
                new int[]{ R.id.courseName, R.id.courseDuration, R.id.courseFee });

        //if (getListView().getAdapter() != null) {
            //SimpleAdapter oldAdapter = (SimpleAdapter) getListView().getAdapter();
            //oldAdapter.notifyDataSetChanged();
        //} else {
            getListView().setAdapter(adapter);
        //}
    }

    private void readCourses() {
        courses = new ArrayList<>();

        try {
            InputStream is = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("[,]");

                if (tokens.length == 3) {
                    courses.add(new Course(tokens[0], tokens[1], tokens[2]));
                }
            }

            br.close();
        } catch (Exception ex) {
            error(ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.course_manager_options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.coursesAddOption:
                Intent intent = new Intent(this, AddCourseActivity.class);

                startActivity(intent);
                break;
            case R.id.coursesDeleteOption:
                intent = new Intent(this, DeleteCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.coursesEditOption:
                intent = new Intent(this, EditCourseActivity.class);
                startActivity(intent);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }
}
