package com.vamsisangam.androidexamples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

import static com.vamsisangam.androidexamples.App.*;
/**
 * Created by Vamsi on 30-05-2017.
 */

public class TimeTickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        log("One minute has passed! - " + new Date().toString());
    }
}
