package com.garagu.marvel.presentation.comic.view.detail;

import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class CharacterRenderer extends RVRenderer<String> {

    @BindView(R.id.txt_name)
    TextView txtName;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_character;
    }

    @Override
    public void render() {
        final String character = getContent();
        txtName.setText(character);
    }

}