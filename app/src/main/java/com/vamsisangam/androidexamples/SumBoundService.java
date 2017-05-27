package com.vamsisangam.androidexamples;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import static com.vamsisangam.androidexamples.App.*;

/**
 * Created by Vamsi on 26-05-2017.
 */

public class SumBoundService extends Service {
    private final IBinder binder = new ResultBinder();

    public class ResultBinder extends Binder {
        public SumBoundService getService() {
            return SumBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        log("onCreate()" + this.getClass().getName());
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("onBind()" + this.getClass().getName());
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        log("onUnbind()" + this.getClass().getName());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        log("onDestroy()" + this.getClass().getName());
        super.onDestroy();
    }

    // custom method performing sum
    public int getSum(int num) {
        log("getSum()" + this.getClass().getName());

        return (num * (num - 1)) / 2;
    }
}
