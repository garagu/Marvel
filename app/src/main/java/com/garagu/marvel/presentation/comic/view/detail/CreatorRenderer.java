package com.garagu.marvel.presentation.comic.view.detail;

import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.ComicCreator;
import com.garagu.marvel.presentation.common.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class CreatorRenderer extends RVRenderer<ComicCreator> {

    @BindView(R.id.txt_role)
    TextView txtRole;
    @BindView(R.id.txt_name)
    TextView txtName;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_creator;
    }

    @Override
    public void render() {
        final ComicCreator creator = getContent();
        txtRole.setText(creator.getRole());
        txtName.setText(creator.getName());
    }

}