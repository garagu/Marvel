package com.garagu.marvel.presentation.character.view.detail;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.Navigator;
import com.garagu.marvel.presentation.character.view.detail.CharacterDetailPresenter.CharacterDetailView;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.ImageLoader;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */
public class CharacterDetailFragment extends BaseFragment implements CharacterDetailView {

    private static final String KEY_SELECTED_CHARACTER = "selectedCharacter";

    @Inject
    CharacterDetailPresenter presenter;
    @Inject
    ImageLoader imageLoader;
    @Inject
    Navigator navigator;

    @BindView(R.id.img_thumbnail_character)
    ImageView imgThumbnail;
    @BindView(R.id.txt_character_name)
    TextView txtName;
    @BindView(R.id.txt_character_description)
    TextView txtDescription;
    @BindView(R.id.layout_character_links)
    LinearLayout layoutLinks;
    @BindView(R.id.txt_characters_links)
    TextView txtLinks;
    @BindView(R.id.card_comics)
    CardView cardComics;
    @BindView(R.id.txt_character_comics_count)
    TextView txtComicsCount;
    @BindView(R.id.txt_character_first_comics)
    TextView txtFirstComics;
    @BindView(R.id.btn_character_comics)
    Button btnComics;
    @BindView(R.id.fab_favorite)
    FloatingActionButton fabFavorite;
    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;
    @BindDimen(R.dimen.margin)
    int margin;
    @SuppressLint("PrivateResource")
    @BindDimen(android.support.design.R.dimen.design_fab_size_normal)
    int fabSize;

    private CharacterViewModel character;

    public static CharacterDetailFragment newInstance(@NonNull CharacterViewModel character) {
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
        setCardComicsMargin();
        initPresenter();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    private void initDependencyInjection() {
        getComponent(CharacterComponent.class).inject(this);
    }

    private void setCardComicsMargin() {
        final LayoutParams layoutParams = (LayoutParams) cardComics.getLayoutParams();
        layoutParams.bottomMargin = fabSize + 2 * margin;
        cardComics.setLayoutParams(layoutParams);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.onInitView(character);
    }

    @OnClick(R.id.btn_character_comics)
    void onMoreComicsClick() {
        presenter.onMoreComicsClick();
    }

    @Override
    public void hideComics() {
        txtComicsCount.setText(character.getComics().getTotalNumber());
        txtFirstComics.setVisibility(View.GONE);
        btnComics.setVisibility(View.GONE);
    }

    @Override
    public void hideLinks() {
        layoutLinks.setVisibility(View.GONE);
    }

    @Override
    public void loadImage(@NonNull String url) {
        imageLoader.load(imgThumbnail, url, placeholder);
    }

    @Override
    public void openComics() {
        navigator.openComics(getActivity(), character.getId());
    }

    @Override
    public void showComics(@NonNull String comics) {
        txtComicsCount.setText(character.getComics().getTotalNumber());
        txtFirstComics.setText(comics);
    }

    @Override
    public void hideImage() {
        imgThumbnail.setVisibility(View.GONE);
    }

    @Override
    public void showDescription(@NonNull String description) {
        txtDescription.setText(description);
    }

    @Override
    public void showFab(boolean isFavorite) {
        final int drawableRes = isFavorite ? R.drawable.ic_favorite_on : R.drawable.ic_favorite_off;
        fabFavorite.setImageResource(drawableRes);
        fabFavorite.setVisibility(View.VISIBLE);
        // final Animation animation = AnimationUtils.loadAnimation(getActivity(), android.support.design.R.anim.design_fab_in);
        // fabFavorite.startAnimation(animation);
    }

    @Override
    public void showLinks(@NonNull Spanned links) {
        txtLinks.setMovementMethod(LinkMovementMethod.getInstance());
        txtLinks.setText(links);
    }

    @Override
    public void showName(@NonNull String name) {
        txtName.setText(character.getName());
    }

    @Override
    public void showUnavailableDescription() {
        TextViewCompat.setTextAppearance(txtDescription, R.style.FieldEmpty);
        txtDescription.setText(R.string.characterdetail_label_description_empty);
    }

}