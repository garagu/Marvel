package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.common.ImageLoader;
import com.garagu.marvel.presentation.common.RVRenderer;

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
        imageLoader.load(imgThumbnail, comic.getUrlThumbnail());
        txtTitle.setText(comic.getTitle());
        txtSeries.setText(comic.getSeriesTitle());
    }

}