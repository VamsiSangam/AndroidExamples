package com.vamsisangam.androidexamples.storage.internal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.vamsisangam.androidexamples.R;
import java.io.PrintWriter;
import static com.vamsisangam.androidexamples.App.*;

public class AddCourseActivity extends Activity {
    EditText courseName, courseDuration, courseFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        init();
    }

    private void init() {
        courseName = (EditText) findViewById(R.id.courseName);
        courseDuration = (EditText) findViewById(R.id.courseDuration);
        courseFee = (EditText) findViewById(R.id.courseFee);
    }

    public void addCourse(View v) {
        String name = courseName.getText().toString();
        String duration = courseDuration.getText().toString();
        String fee = courseFee.getText().toString();
        Course course = new Course(name, fee, duration);

        writeDataToFile(course.toString());
        courseName.setText("");
        courseDuration.setText("");
        courseFee.setText("");
    }

    private void writeDataToFile(String line) {
        try {
            PrintWriter pw = new PrintWriter(openFileOutput(CourseManagerActivity.FILE_NAME, MODE_APPEND));

            pw.println(line);
            pw.close();
            Toast.makeText(this, "Data written successfully!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            error(ex.getMessage());
            Toast.makeText(this, "Exception occurred!", Toast.LENGTH_LONG).show();
        }
    }
}
