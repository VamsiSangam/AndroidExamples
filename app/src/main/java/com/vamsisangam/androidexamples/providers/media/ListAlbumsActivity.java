package com.vamsisangam.androidexamples.providers.media;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.vamsisangam.androidexamples.R;

public class ListAlbumsActivity extends Activity {
    Spinner spinnerMediaLocation;
    ListView listAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_albums);
        init();

        spinnerMediaLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Uri uri = null;
                Cursor cursor = null;
                String[] from = null;
                int[] to = null;
                SimpleCursorAdapter adapter = null;

                switch (i) {
                    case 0:
                        uri = MediaStore.Audio.Albums.INTERNAL_CONTENT_URI;
                        cursor = getContentResolver().query(uri, null, null, null, null);

                        if (cursor == null) {
                            return;
                        }

                        from = new String[]{MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST};
                        to = new int[]{R.id.title, R.id.description};
                        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, cursor, from, to, 0);
                        listAlbums.setAdapter(adapter);
                        break;
                    case 1:
                        uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
                        cursor = getContentResolver().query(uri, null, null, null, null);

                        if (cursor == null) {
                            return;
                        }

                        from = new String[]{MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST};
                        to = new int[]{R.id.title, R.id.description};
                        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, cursor, from, to, 0);
                        listAlbums.setAdapter(adapter);
                        break;
                    case 2:
                        uri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
                        cursor = getContentResolver().query(uri, null, null, null, null);

                        if (cursor == null) {
                            return;
                        }

                        from = new String[]{MediaStore.Images.Media.DISPLAY_NAME,
                                MediaStore.Images.Media.DATA};
                        to = new int[]{R.id.title, R.id.description};
                        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, cursor, from, to, 0);
                        listAlbums.setAdapter(adapter);
                        break;
                    case 3:
                        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        cursor = getContentResolver().query(uri, null, null, null, null);

                        if (cursor == null) {
                            return;
                        }

                        from = new String[]{MediaStore.Images.Media.DISPLAY_NAME,
                                MediaStore.Images.Media.DATA};
                        to = new int[]{R.id.title, R.id.description};
                        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.list_item, cursor, from, to, 0);
                        listAlbums.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // do nothing
            }
        });
    }

    private void init() {
        spinnerMediaLocation = (Spinner) findViewById(R.id.spinnerMediaLocation);
        listAlbums = (ListView) findViewById(R.id.listAlbums);
    }
}
