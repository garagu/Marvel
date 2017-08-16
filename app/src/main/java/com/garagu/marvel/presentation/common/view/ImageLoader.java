package com.garagu.marvel.presentation.common.view;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by garagu.
 */
public interface ImageLoader {
    void load(ImageView imageView, String url, Drawable placeholder);
}