package com.vamsisangam.androidexamples.providers.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.vamsisangam.androidexamples.R;

import java.io.FileInputStream;
import java.util.ArrayList;

import static com.vamsisangam.androidexamples.App.log;

/**
 * Created by Vamsi on 10-06-2017.
 */

public class GalleryAdapter extends ArrayAdapter<String> {
    public Context ctx;
    public ArrayList<String> imagePaths;
    final int THUMBNAIL_SIZE = 512;

    public GalleryAdapter(Context ctx, ArrayList<String> imagePaths) {
        super(ctx, R.layout.activity_view_photo, imagePaths);
        this.ctx = ctx;
        this.imagePaths = imagePaths;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);

            convertView = inflater.inflate(R.layout.activity_view_photo, parent, false);
        }

        try {
            ImageView img = (ImageView) convertView.findViewById(R.id.image);
            final String path = imagePaths.get(position);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 16;
            Bitmap preview_bitmap = BitmapFactory.decodeStream(new FileInputStream(path), null, options);
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(preview_bitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
            img.setImageBitmap(thumbnail);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, ViewPhotoActivity.class);

                    intent.putExtra("path", path);

                    try {
                        ctx.startActivity(intent);
                    } catch (Exception ex) {
                        log("Exception - " + ex.getMessage());
                    }
                }
            });
        } catch (Exception ex) {
            log("Exception - " + ex.getMessage());
        }

        return convertView;
    }
}
