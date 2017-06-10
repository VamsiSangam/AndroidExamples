package com.vamsisangam.androidexamples.providers.gallery;

import android.content.res.Resources;

/**
 * Created by Vamsi on 11-06-2017.
 */

public class Image {
    String path;
    int height, width;

    public Image(String path, int height, int width) {
        this.path = path;
        this.height = height;
        this.width = width;
    }

    public String getPath() {
        return path;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getScaleRatioToHalfScreen() {
        int requiredWidth = Resources.getSystem().getDisplayMetrics().widthPixels / 2;

        if (requiredWidth < width) {
            return width / requiredWidth;
        } else {
            return 1;
        }
    }

    public int getScaleRatioToFullScreen() {
        int requiredWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

        if (requiredWidth < width) {
            return width / requiredWidth;
        } else {
            return 1;
        }
    }
}
