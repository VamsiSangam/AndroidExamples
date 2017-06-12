package com.vamsisangam.androidexamples.networking.countries;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

import org.w3c.dom.Text;

import java.net.URL;

import static com.vamsisangam.androidexamples.App.log;

public class CountryDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        if (getIntent() != null) {
            Intent intent = getIntent();

            TextView title = (TextView) findViewById(R.id.title);
            TextView description = (TextView) findViewById(R.id.description);
            TextView fullDescription = (TextView) findViewById(R.id.fullDescription);
            final ImageView image = (ImageView) findViewById(R.id.thumbnail);
            final String imageURL = intent.getStringExtra("flag");

            (new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(imageURL);
                        final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        image.post(new Runnable() {
                            @Override
                            public void run() {
                                image.setImageBitmap(bmp);
                            }
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();

            title.setText(intent.getStringExtra("name"));
            description.setText(intent.getStringExtra("capital"));
            fullDescription.setText(intent.getStringExtra("details"));
        } else {
            Toast.makeText(this, "No country information in intent. Invalid invocation.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
