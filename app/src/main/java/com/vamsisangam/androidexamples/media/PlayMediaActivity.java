package com.vamsisangam.androidexamples.media;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vamsisangam.androidexamples.R;

import java.io.IOException;

public class PlayMediaActivity extends Activity implements MediaPlayer.OnCompletionListener {
    MediaPlayer mp;
    boolean isBackgroundMusicPlaying = false;

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        isBackgroundMusicPlaying = false;
        Toast.makeText(this, "Completed!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_media);

        mp = MediaPlayer.create(this, R.raw.crows);
        mp.setOnCompletionListener(this);
    }

    public void playAudioWithoutIntent(View v) {
        if (!isBackgroundMusicPlaying) {
            mp.start();

            isBackgroundMusicPlaying = true;
        }

        // If you want to stop, use mp.stop(); mp.prepare();
        // before you again call mp.start();
    }

    public void playAudioWithIntent(View v) {
        Uri audio = Uri.parse("android.resource://com.vamsisangam.androidexamples/raw/rain");

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setDataAndType(audio, "audio/*");
        startActivity(intent);
    }

    public void playVideoWithoutIntent(View v) {

    }

    public void playVideoWithIntent(View v) {

    }
}
