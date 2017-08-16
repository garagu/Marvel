package com.garagu.marvel.presentation.comic.view.detail.gallery;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.ImageLoader;

import javax.inject.Inject;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ImageDetailFragment extends BaseFragment {

    private static final String KEY_URL = "url";

    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.img_gallery_item)
    ImageView imageView;
    @BindDrawable(R.drawable.placeholder_character)
    Drawable placeholder;

    private String url;

    public static ImageDetailFragment newInstance(String url) {
        final ImageDetailFragment fragment = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putString(KEY_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_gallery;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(KEY_URL);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initComponent();
    }

    private void initDependencyInjection() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initComponent() {
        imageLoader.load(imageView, url, placeholder);
    }

}