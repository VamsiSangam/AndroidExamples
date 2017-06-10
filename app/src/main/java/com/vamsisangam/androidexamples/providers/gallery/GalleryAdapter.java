package com.vamsisangam.androidexamples.providers.gallery;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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

public class GalleryAdapter extends ArrayAdapter<Image> {
    public Context ctx;
    public ArrayList<Image> images;
    final int THUMBNAIL_SIZE, SCREEN_WIDTH;

    public GalleryAdapter(Context ctx, ArrayList<Image> images) {
        super(ctx, R.layout.activity_view_photo, images);
        this.ctx = ctx;
        this.images = images;

        SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        THUMBNAIL_SIZE = SCREEN_WIDTH / 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);

            convertView = inflater.inflate(R.layout.activity_view_photo, parent, false);
        }

        try {
            ImageView img = (ImageView) convertView.findViewById(R.id.image);
            final Image image = images.get(position);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = image.getScaleRatioToHalfScreen();
            Bitmap preview_bitmap = BitmapFactory.decodeStream(new FileInputStream(image.getPath()), null, options);
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(preview_bitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE);
            img.setImageBitmap(thumbnail);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, ViewPhotoActivity.class);

                    intent.putExtra("path", image.getPath());
                    intent.putExtra("height", image.getHeight());
                    intent.putExtra("width", image.getWidth());

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
