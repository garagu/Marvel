package com.garagu.marvel.presentation.character.view.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
public class CharacterDetailFragment extends BaseFragment {

    private static final String KEY_SELECTED_CHARACTER = "selectedCharacter";

    @Inject
    Picasso picasso;

    @BindView(R.id.img_thumbnail_character)
    ImageView imgThumbnail;
    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;

    private CharacterViewModel character;

    public static CharacterDetailFragment newInstance(CharacterViewModel character) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_SELECTED_CHARACTER, character);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_character_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        character = getArguments().getParcelable(KEY_SELECTED_CHARACTER);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initView();
    }

    private void initDependencyInjection() {
        getComponent(CharacterComponent.class).inject(this);
    }

    private void initView() {
        if (character.isThumbnailAvailable()) {
            picasso.load(character.getUrlThumbnail()).into(imgThumbnail);
        } else {
            imgThumbnail.setImageDrawable(placeholder);
        }
    }

}