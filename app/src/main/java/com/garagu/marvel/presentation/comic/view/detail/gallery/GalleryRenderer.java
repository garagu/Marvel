package com.garagu.marvel.presentation.comic.view.detail.gallery;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
class GalleryRenderer extends RVRenderer<String> {

    @BindView(R.id.img_gallery_item)
    ImageView imageView;
    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;

    private final ImageLoader imageLoader;
    private final int cellSize;

    GalleryRenderer(@NonNull OnRendererClickListener<String> clickListener, ImageLoader imageLoader, int cellSize) {
        super(clickListener);
        this.imageLoader = imageLoader;
        this.cellSize = cellSize;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_gallery;
    }

    @Override
    public void render() {
        overrideSize();
        imageLoader.load(imageView, getContent(), placeholder);
    }

    private void overrideSize() {
        imageView.getLayoutParams().height = cellSize;
        imageView.getLayoutParams().width = cellSize;
        imageView.setScaleType(ScaleType.CENTER_CROP);
    }

}