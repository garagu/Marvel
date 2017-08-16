package com.garagu.marvel.presentation.comic.view.detail.info;

import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.model.CreatorViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class CreatorRenderer extends RVRenderer<CreatorViewModel> {

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
        final CreatorViewModel creator = getContent();
        txtRole.setText(creator.getRole());
        txtName.setText(creator.getName());
    }

}