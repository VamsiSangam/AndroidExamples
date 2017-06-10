package com.vamsisangam.androidexamples.providers.gallery;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
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

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_view_photo);
        init();

        try {
            Intent intent = getIntent();
            String path = intent.getStringExtra("path");
            int height = intent.getIntExtra("height", 500);
            int width = intent.getIntExtra("width", 500);
            Image imageDetails = new Image(path, height, width);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = imageDetails.getScaleRatioToFullScreen();
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
