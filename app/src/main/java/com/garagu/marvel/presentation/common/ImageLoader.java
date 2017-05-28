package com.garagu.marvel.presentation.common;

import android.widget.ImageView;

import com.garagu.marvel.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class ImageLoader {

    private Picasso picasso;

    @Inject
    ImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    public void load(ImageView imageView, String url) {
        picasso.load(url).placeholder(R.mipmap.placeholder).into(imageView);
    }

}