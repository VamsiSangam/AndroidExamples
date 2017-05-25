package com.vamsisangam.androidexamples;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Vamsi on 25-05-2017.
 */

public class SumIntentService extends IntentService {
    public SumIntentService() {
        super("Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final int num = intent.getIntExtra("number", 0);

        try {
            int sum = 0;

            for (int i = 1; i <= num; ++i) {
                Thread.sleep(1000);
                sum += i;
            }

            sendNotification("Total Calculated!", "The total is = " + sum + ". Done by intent service.");
        } catch (Exception ex) {
            Log.d("AndroidExamples", "Exception in SumServiceAcitvity - " + ex.getMessage());
        }
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
