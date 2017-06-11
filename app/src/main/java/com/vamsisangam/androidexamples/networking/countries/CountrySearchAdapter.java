package com.vamsisangam.androidexamples.networking.countries;

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
import android.widget.TextView;
import com.vamsisangam.androidexamples.R;
import com.vamsisangam.androidexamples.util.ImageHelper;
import java.net.URL;
import java.util.ArrayList;

import static com.vamsisangam.androidexamples.App.log;

/**
 * Created by Vamsi on 11-06-2017.
 */

public class CountrySearchAdapter extends ArrayAdapter<Country> {
    Context context;
    ArrayList<Country> countries;
    final int THUMBNAIL_SIZE;

    public CountrySearchAdapter(Context context, ArrayList<Country> countries) {
        super(context, R.layout.list_item_title_with_photo, countries);
        this.context = context;
        this.countries = countries;

        THUMBNAIL_SIZE = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.list_item_title_with_photo, parent, false);
        }

        try {
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView description = (TextView) convertView.findViewById(R.id.description);
            TextView info = (TextView) convertView.findViewById(R.id.info);
            final ImageView image = (ImageView) convertView.findViewById(R.id.thumbnail);
            final Country country = countries.get(position);

            title.setText(country.getName());
            log("In adapter for " + country.getName());
            description.setText("Capital - " + country.getCapital());
            info.setText("Alpha2 - " + country.getAlpha2Code()
                    + ", Alpha3 - " + country.getAlpha3Code());

            (new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(country.getFlagURL());
                        final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        image.post(new Runnable() {
                            @Override
                            public void run() {
                                image.setImageBitmap(bmp);
                            }
                        });
                    } catch (Exception ex) {
                        log("Exception 2 - " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception ex) {
            log("Exception 3 - " + ex.getMessage());
            ex.printStackTrace();
        }

        return convertView;
    }
}
