package com.vamsisangam.androidexamples.providers.gallery;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import com.vamsisangam.androidexamples.R;

import java.io.File;
import java.io.FileInputStream;

import static com.vamsisangam.androidexamples.App.log;

public class ViewPhotoActivity extends Activity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);
        init();

        try {
            String path = getIntent().getStringExtra("path");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap preview_bitmap = BitmapFactory.decodeStream(new FileInputStream(path), null, options);
            image.setImageBitmap(preview_bitmap);
        } catch (Exception ex) {
            log("Exception - " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void init() {
        image = (ImageView) findViewById(R.id.image);
    }
}
