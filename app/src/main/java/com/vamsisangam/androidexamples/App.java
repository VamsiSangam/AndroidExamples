package com.vamsisangam.androidexamples;

import android.util.Log;

/**
 * Created by Vamsi on 25-05-2017.
 */

public class App {
    // Global constants
    public static String TAG = "Android Examples";

    // Utility functions
    public static void log(String message) {
        Log.d(TAG, message);
    }

    public static void error(String message) {
        Log.e(TAG, message);
    }
}
