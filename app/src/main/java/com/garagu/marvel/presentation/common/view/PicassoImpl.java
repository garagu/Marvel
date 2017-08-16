package com.garagu.marvel.presentation.common.view;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class PicassoImpl implements ImageLoader {

    private final Picasso picasso;

    @Inject
    public PicassoImpl(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(ImageView imageView, String url, Drawable placeholder) {
        picasso.load(url).placeholder(placeholder).into(imageView);
    }

}