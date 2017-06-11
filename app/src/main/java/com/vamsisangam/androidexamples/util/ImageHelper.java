package com.vamsisangam.androidexamples.util;

import android.content.res.Resources;

/**
 * Created by Vamsi on 11-06-2017.
 */

public class ImageHelper {
    public static int getImageScaleDownFactor(int imageWidth, int screenToImageWidthRatio) {
        int requiredWidth = Resources.getSystem().getDisplayMetrics().widthPixels
                    / screenToImageWidthRatio;

        if (requiredWidth < imageWidth) {
            return imageWidth / requiredWidth;
        } else {
            return 1;
        }
    }
}
