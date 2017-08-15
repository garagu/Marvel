package com.garagu.marvel.presentation.comic.view.list;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
class ComicRenderer extends RVRenderer<ComicViewModel> {

    @BindView(R.id.img_thumbnail)
    ImageView imgThumbnail;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_series)
    TextView txtSeries;
    @BindDrawable(R.mipmap.placeholder)
    Drawable placeholder;

    private final ImageLoader imageLoader;

    ComicRenderer(@NonNull OnRendererClickListener<ComicViewModel> clickListener, ImageLoader imageLoader) {
        super(clickListener);
        this.imageLoader = imageLoader;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_comic;
    }

    @Override
    public void render() {
        final ComicViewModel comic = getContent();
        imageLoader.load(imgThumbnail, comic.getUrlThumbnail(), placeholder);
        txtTitle.setText(comic.getTitle());
        txtSeries.setText(comic.getSeriesTitle());
    }

}