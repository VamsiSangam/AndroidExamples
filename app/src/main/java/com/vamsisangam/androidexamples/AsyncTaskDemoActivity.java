package com.vamsisangam.androidexamples;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskDemoActivity extends Activity {
    EditText number;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);
        init();
    }

    private void init() {
        number = (EditText) findViewById(R.id.AsyncTaskDemoNumber);
        result = (TextView) findViewById(R.id.AsyncTaskDemoResult);
    }

    public void calculateSum(View v) {
        SumTask task = new SumTask();

        task.execute(number.getText().toString());
    }

    class SumTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            result.setText("Calculation started...");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            // Notify user of result
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(AsyncTaskDemoActivity.this);
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.bell);

            builder.setSmallIcon(R.drawable.bell);
            builder.setLargeIcon(bm);

            Intent notificationIntent = new Intent(AsyncTaskDemoActivity.this, HomeActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(AsyncTaskDemoActivity.this, 1, notificationIntent, 0);
            builder.setContentIntent(contentIntent);
            builder.setContentTitle("Sum has been calculated!");
            builder.setContentText("The sum is = " + integer);
            builder.setAutoCancel(true);
            builder.setDefaults(Notification.DEFAULT_SOUND);
            builder.setDefaults(Notification.DEFAULT_VIBRATE);
            builder.setWhen(System.currentTimeMillis());
            manager.notify(2, builder.build());

            result.setText("Sum calculation finished! Check your notifications!");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];

            result.setText(progress + " numbers added...");
        }

        @Override
        protected Integer doInBackground(String... strings) {
            int sum = 0;
            int num = Integer.parseInt(strings[0]);

            for (int i = 1; i <= num; ++i) {
                try {
                    Thread.sleep(1000);
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Exception - " + ex.getMessage(), Toast.LENGTH_LONG).show();
                }

                sum += i;
                this.publishProgress(i);
            }

            return sum;
        }
    }
}
