package com.garagu.marvel.presentation.comic.view.detail.info;

import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class CharacterRenderer extends RVRenderer<String> {

    @BindView(R.id.txt_name)
    TextView txtName;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_comic_character;
    }

    @Override
    public void render() {
        final String character = getContent();
        txtName.setText(character);
    }

}