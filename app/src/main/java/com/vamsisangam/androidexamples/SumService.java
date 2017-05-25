package com.vamsisangam.androidexamples;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Vamsi on 25-05-2017.
 */

public class SumService extends Service {
    public SumService() {
        // no argument constructor is a must
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("AndroidExamples", "onCreate() of SumService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final int num = intent.getIntExtra("number", 0);

        new Thread() {
            @Override
            public void run() {
                try {
                    int sum = 0;

                    for (int i = 1; i <= num; ++i) {
                        Thread.sleep(1000);
                        sum += i;
                    }

                    sendNotification("Total Calculated!", "The total is = " + sum + ". Done by service.");
                    stopSelf();
                } catch (Exception ex) {
                    Log.d("AndroidExamples", "Exception in SumServiceAcitvity - " + ex.getMessage());
                }
            }
        }.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("AndroidExamples", "onDestroy() of SumService");
    }

    private void sendNotification(String title, String contentText) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.exit);

        builder.setSmallIcon(R.drawable.bell);
        builder.setLargeIcon(bm);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent, 0);

        builder.setContentIntent(contentIntent);
        builder.setContentTitle(title);
        builder.setContentText(contentText);
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setWhen(System.currentTimeMillis());
        nm.notify((int) System.currentTimeMillis(), builder.build());
    }
}