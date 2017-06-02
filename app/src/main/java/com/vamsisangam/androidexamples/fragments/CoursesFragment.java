package com.vamsisangam.androidexamples.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.vamsisangam.androidexamples.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        String description = "";

        switch (checkedId) {
            case R.id.rbJava:
                description = "Java SE is the most widely used language in the world!";
                break;
            case R.id.rbAndroid:
                description = "Android is the world's most widely used mobile operating system.";
                break;
            case R.id.rbCSharp:
                description = "C# pronounced as \"C Sharp\" is a very powerful language with many powerful programming constructs.";
                break;
        }

        CourseFragmentsActivity activity = (CourseFragmentsActivity) getActivity(); // bad design, do it with interface
        activity.setCourseDescription(description);
    }

    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_courses, container, false);
        RadioGroup courses = (RadioGroup) v.findViewById(R.id.rgCourse);

        courses.setOnCheckedChangeListener(this);

        return v;
    }

}
