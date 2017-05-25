package com.garagu.marvel.presentation.common;

import android.content.Context;
import android.widget.ImageView;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.R;
import com.squareup.picasso.Picasso;

/**
 * Created by garagu.
 */
public class ImageLoader {

    public static void load(Context context, ImageView imageView, String url) {
        Picasso picasso = new Picasso.Builder(context)
                .listener((p, uri, e) -> {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace();
                    }
                })
                .build();
        picasso.load(url).placeholder(R.mipmap.placeholder).into(imageView);
    }

}