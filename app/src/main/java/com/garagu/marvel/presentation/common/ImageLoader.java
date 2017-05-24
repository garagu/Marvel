package com.garagu.marvel.presentation.common;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by garagu.
 */
public class ImageLoader {

    public static void load(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);
    }

}