package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class NoticationDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notication_demo);
    }

    public void displayOneNotification(View v) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.exit);

        builder.setSmallIcon(R.drawable.bell);
        builder.setLargeIcon(bm);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent, 0);

        builder.setContentIntent(contentIntent);
        builder.setContentTitle("\"Hi!\"");
        builder.setContentText("Notification from Android Examples!");
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setWhen(System.currentTimeMillis());
        nm.notify(2, builder.build());
    }

    public void displayThreeNotifications(View v) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.exit);

        builder.setSmallIcon(R.drawable.bell);
        builder.setLargeIcon(bm);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent, 0);

        builder.setContentIntent(contentIntent);
        builder.setContentTitle("Hi!");
        builder.setContentText("First Notification!");
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setWhen(System.currentTimeMillis());
        nm.notify(1, builder.build());

        builder.setContentTitle("Hi again!");
        builder.setContentText("Second Notification!");
        builder.setWhen(System.currentTimeMillis());
        nm.notify(2, builder.build());

        builder.setContentTitle("Hi once again!!");
        builder.setContentText("Third Notification!");
        builder.setWhen(System.currentTimeMillis());
        nm.notify(3, builder.build());
    }

    public void displayDelayedNotification(View v) {
        final NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final Notification.Builder builder = new Notification.Builder(this);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.exit);

        builder.setSmallIcon(R.drawable.bell);
        builder.setLargeIcon(bm);

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1, notificationIntent, 0);

        builder.setContentIntent(contentIntent);
        builder.setContentTitle("Hi!");
        builder.setContentText("First Notification!");
        builder.setAutoCancel(true);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);


        (new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Log.d("Today", "InterruptedException in NotificatioDemoActivity:displayDelayedNotification");
                }

                builder.setWhen(System.currentTimeMillis());
                nm.notify(1, builder.build());
            }
        }).start();
    }
}
