package com.vamsisangam.androidexamples.providers.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.vamsisangam.androidexamples.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static com.vamsisangam.androidexamples.App.log;

public class GalleryActivity extends Activity {
    TableLayout tableLayoutInternal, tableLayoutExternal;
    final int THUMBSIZE = 512;
    GridView galleryGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        init();
        loadImages();
    }

    private void init() {
        galleryGrid = (GridView) findViewById(R.id.galleryGrid);
    }

    private void loadImages() {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        ArrayList<String> imgPaths = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String imgPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                imgPaths.add(imgPath);
            }
        }

        GalleryAdapter adapter = new GalleryAdapter(this, imgPaths);
        galleryGrid.setAdapter(adapter);
    }
}
