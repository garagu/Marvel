package com.garagu.marvel.presentation.favorites.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.model.FavoriteViewModel;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
class MyFavoriteRenderer extends RVRenderer<FavoriteViewModel> {

    private final ImageLoader imageLoader;
    @BindView(R.id.txt_favorite_name)
    TextView txtName;
    @BindView(R.id.img_favorite_thumbnail)
    ImageView imgThumbnail;
    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;

    MyFavoriteRenderer(@NonNull OnRendererClickListener<FavoriteViewModel> clickListener, ImageLoader imageLoader) {
        super(clickListener);
        this.imageLoader = imageLoader;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_favorite;
    }

    @Override
    public void render() {
        final FavoriteViewModel favorite = getContent();
        txtName.setText(favorite.getName());
        if (favorite.isThumbnailAvailable()) {
            imageLoader.load(imgThumbnail, favorite.getUrlThumbnail(), placeholder);
        } else {
            imgThumbnail.setImageDrawable(placeholder);
        }
    }

}