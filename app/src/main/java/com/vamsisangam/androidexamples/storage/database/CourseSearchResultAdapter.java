package com.vamsisangam.androidexamples.storage.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vamsisangam.androidexamples.R;

import java.util.ArrayList;
import static com.vamsisangam.androidexamples.App.*;

/**
 * Created by Vamsi on 09-06-2017.
 */

public class CourseSearchResultAdapter extends ArrayAdapter<Course> {
    private Context context;
    private ArrayList<Course> courses;

    public CourseSearchResultAdapter(Context ctx, ArrayList<Course> courses) {
        super(ctx, R.layout.adapter_course_search_result, courses);
        this.context = ctx;
        this.courses = courses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.adapter_course_search_result, parent, false);
        }

        Button btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
        Button btnTopics = (Button) convertView.findViewById(R.id.btnTopics);
        TextView courseName = (TextView) convertView.findViewById(R.id.courseName);
        TextView courseDuration = (TextView) convertView.findViewById(R.id.courseDuration);
        TextView courseFee = (TextView) convertView.findViewById(R.id.courseFee);
        final Course course  = courses.get(position);

        courseName.setText(course.getName());
        courseDuration.setText(course.getDuration() + " sessions");
        courseFee.setText("Rs. " + course.getFee());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CourseEditRemoveActivity.class);

                intent.putExtra("ID", course.getId());
                context.startActivity(intent);
            }
        });

        btnTopics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListTopicsActivity.class);

                intent.putExtra("ID", course.getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
