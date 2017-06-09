package com.vamsisangam.androidexamples.storage.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vamsisangam.androidexamples.R;

import java.util.ArrayList;

/**
 * Created by Vamsi on 09-06-2017.
 */

public class CourseTopicsListAdapter extends ArrayAdapter<Topic> {
    private Context context;
    private ArrayList<Topic> topics;

    public CourseTopicsListAdapter(Context ctx, ArrayList<Topic> topics) {
        super(ctx, R.layout.adapter_course_topics_list, topics);
        this.context = ctx;
        this.topics = topics;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.adapter_course_topics_list, parent, false);
        }

        Button btnDeleteTopic = (Button) convertView.findViewById(R.id.btnDeleteTopic);
        TextView topicName = (TextView) convertView.findViewById(R.id.topicName);
        TextView topicDuration = (TextView) convertView.findViewById(R.id.topicDuration);
        final Topic topic  = topics.get(position);

        topicName.setText(topic.getName());
        topicDuration.setText(topic.getDuration() + " sessions");

        btnDeleteTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTopicActivity activity = (AddTopicActivity) context;

                activity.deleteTopic(topic.getId());
            }
        });

        return convertView;
    }
}
