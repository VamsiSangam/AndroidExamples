package com.vamsisangam.androidexamples.storage.database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

import java.util.ArrayList;

import static com.vamsisangam.androidexamples.App.error;

public class AddTopicActivity extends Activity {
    TextView txtTitle, txtTopicsListTitle;
    EditText topicName, topicDuration;
    ListView listTopics;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);
        init();
        course = getCourse(getIntent().getStringExtra("ID"));

        if (course == null) {
            Toast.makeText(this, "Invalid course selected!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        txtTitle.setText("Add topics to " + course.getName());
        readTopics();
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

    private void readTopics() {
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

            listTopics.setAdapter(adapter);
            txtTopicsListTitle.setText(topics.size() + " topics already added to this course -");
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTopicsListTitle = (TextView) findViewById(R.id.txtTopicsListTitle);
        topicName = (EditText) findViewById(R.id.topicName);
        topicDuration = (EditText) findViewById(R.id.topicDuration);
        listTopics = (ListView) findViewById(R.id.listTopics);
    }

    public void addTopic(View v) {
        try {
            String name = topicName.getText().toString();
            String duration = topicDuration.getText().toString();
            CourseDbHelper courseDb = new CourseDbHelper(this);
            SQLiteDatabase db = courseDb.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(CourseDbHelper.COL_NAME, name);
            values.put(CourseDbHelper.COL_DURATION, duration);
            values.put(CourseDbHelper.COL_COURSE, course.getId());

            db.insert(CourseDbHelper.TABLE_TOPICS, null, values);

            Toast.makeText(this, "Topic added to course successfully!", Toast.LENGTH_LONG).show();
            topicName.setText("");
            topicDuration.setText("");
            readTopics();   // reset the list of topics
        } catch (Exception ex) {
            error(ex.getMessage());
        }
    }

    public void deleteTopic(String topicId) {
        try {
            CourseDbHelper dbHelper = new CourseDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            int count = db.delete(CourseDbHelper.TABLE_TOPICS, CourseDbHelper.COL_ID + " = ?",
                    new String[]{topicId});

            if (count == 1) {
                Toast.makeText(this, "Topic deleted successfully!", Toast.LENGTH_LONG).show();
                readTopics();
            } else {
                Toast.makeText(this, "Topic not found!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
