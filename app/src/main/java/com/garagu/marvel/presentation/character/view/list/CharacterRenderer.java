package com.garagu.marvel.presentation.character.view.list;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;
import com.squareup.picasso.Picasso;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */
class CharacterRenderer extends RVRenderer<CharacterViewModel> {

    @BindView(R.id.img_thumbnail_character)
    ImageView imgThumbnail;
    @BindView(R.id.txt_character_name)
    TextView txtName;
    //@BindView(R.id.img_favorite)
    //LottieAnimationView imgFavorite;

    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;

    private final Picasso picasso;
    private final OnCardClickListener onCardClickListener;

    CharacterRenderer(@NonNull Picasso picasso, @NonNull OnCardClickListener onCardClickListener) {
        this.picasso = picasso;
        this.onCardClickListener = onCardClickListener;
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
        final String transitionName = getContext().getString(R.string.transition_character_detail) + character.getId();
        imgThumbnail.setTransitionName(transitionName);
    }

    @OnClick(R.id.img_thumbnail_character)
    void onThumbnailClick(View view) {
        onCardClickListener.onThumbnailClick(view, getContent());
    }

    /*
    @OnClick(R.id.img_favorite)
    void onFavoriteClick() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000);
        animator.addUpdateListener(animation -> {
            final float progress = (float) animation.getAnimatedValue();
            imgFavorite.setProgress(progress);
        });
        animator.start();
    }
    */

    interface OnCardClickListener {
        void onFavoriteClick(@NonNull CharacterViewModel character);

        void onThumbnailClick(@NonNull View view, @NonNull CharacterViewModel character);
    }

}