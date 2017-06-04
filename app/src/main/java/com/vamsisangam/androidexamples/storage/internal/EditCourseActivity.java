package com.vamsisangam.androidexamples.storage.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static com.vamsisangam.androidexamples.App.error;

public class EditCourseActivity extends Activity {
    Spinner selectCourse;
    ArrayList<Course> courses = new ArrayList<>();
    EditText courseName, courseDuration, courseFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        init();
    }

    private void init() {
        courseName = (EditText) findViewById(R.id.courseName);
        courseDuration = (EditText) findViewById(R.id.courseDuration);
        courseFee = (EditText) findViewById(R.id.courseFee);
        selectCourse = (Spinner) findViewById(R.id.selectCourse);
        readCourses();

        SimpleAdapter adapter = new SimpleAdapter(this, getDataForAdapter(),
                R.layout.spinner_item_course,
                new String[] {"name"},
                new int[]{R.id.spinnerItemCourseName});

        selectCourse.setAdapter(adapter);

        selectCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Course course = courses.get(i);

                courseName.setText(course.getName());
                courseDuration.setText(course.getRawDuration());
                courseFee.setText(course.getRawFee());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                courseName.setText("");
                courseDuration.setText("");
                courseFee.setText("");
            }
        });
    }

    public void saveChanges(View v) {
        int selectItem = selectCourse.getSelectedItemPosition();

        Course course = courses.get(selectItem);

        course.setName(courseName.getText().toString());
        course.setDuration(courseDuration.getText().toString());
        course.setFee(courseFee.getText().toString());

        writeCourses();
        finish();
    }

    private void writeCourses() {
        try {
            OutputStream outputStream = openFileOutput(CourseManagerActivity.FILE_NAME, MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(outputStream);

            for (Course course : courses) {
                pw.println(course.toString());
            }

            pw.close();
            Toast.makeText(this, "Courses successfully modified!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            error(ex.getMessage());
        }
    }

    private void readCourses() {
        try {
            InputStream is = openFileInput(CourseManagerActivity.FILE_NAME);
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

    private ArrayList<HashMap<String, String>> getDataForAdapter() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        for (Course course : courses) {
            data.add(course.getMap());
        }

        return data;
    }
}
