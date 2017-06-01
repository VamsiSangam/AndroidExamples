package com.vamsisangam.androidexamples.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.vamsisangam.androidexamples.R;

public class FragmentsDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_demo);
    }

    public void addFragment(View v) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.FragmentsDemoContainer, new SecondFragment(), "second");
        ft.commit();
    }

    public void removeFragment(View v) {
        FragmentManager fm = getFragmentManager();
        Fragment f = fm.findFragmentByTag("second");


        if (f != null) {
            FragmentTransaction ft = fm.beginTransaction();

            ft.remove(f);
            ft.commit();
        }
    }

    public void replaceFragment(View v) {
        FragmentManager fm = getFragmentManager();
        Fragment f = fm.findFragmentByTag("second");

        if (f != null) {
            FragmentTransaction ft = fm.beginTransaction();

            ft.replace(R.id.FragmentsDemoContainer, new ThirdFragment(), "second");
            ft.commit();
        }
    }
}
