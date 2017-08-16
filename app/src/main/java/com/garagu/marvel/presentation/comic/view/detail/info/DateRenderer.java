package com.garagu.marvel.presentation.comic.view.detail.info;

import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.model.ComicDateViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class DateRenderer extends RVRenderer<ComicDateViewModel> {

    @BindView(R.id.txt_role)
    TextView txtType;
    @BindView(R.id.txt_name)
    TextView txtDate;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_creator;
    }

    @Override
    public void render() {
        final ComicDateViewModel comicDate = getContent();
        txtType.setText(comicDate.getType());
        txtDate.setText(comicDate.getDate());
    }

}