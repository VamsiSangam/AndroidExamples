package com.vamsisangam.androidexamples.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vamsisangam.androidexamples.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseDescriptionFragment extends Fragment {
    View v;

    public CourseDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_course_description, container, false);

        return v;
    }

    public void setDescription(String s) {
        TextView tv = (TextView) v.findViewById(R.id.txtCourseDescription);

        tv.setText(s);
    }

}
