package com.garagu.marvel.presentation.character.view.list;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;
import com.squareup.picasso.Picasso;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
class CharacterRenderer extends RVRenderer<CharacterViewModel> {

    @BindView(R.id.img_thumbnail_character)
    ImageView imgThumbnail;
    @BindView(R.id.txt_character_name)
    TextView txtName;
    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;

    private final Picasso picasso;

    CharacterRenderer(@NonNull OnRendererClickListener<CharacterViewModel> clickListener, Picasso picasso) {
        super(clickListener);
        this.picasso = picasso;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_character;
    }

    @Override
    public void render() {
        final CharacterViewModel character = getContent();
        txtName.setText(character.getName());
        if (character.isThumbnailAvailable()) {
            picasso.load(character.getUrlThumbnail()).placeholder(placeholder).into(imgThumbnail);
        } else {
            imgThumbnail.setImageDrawable(placeholder);
        }
    }

}