package com.garagu.marvel.presentation.comic.view.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.garagu.marvel.presentation.common.model.ViewConfig;
import com.garagu.marvel.presentation.common.view.BottomNavActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class ComicDetailActivity extends BottomNavActivity implements HasInjection<ComicComponent> {

    private static final String KEY_SELECTED_COMIC = "selectedComic";

    @Inject
    Navigator navigator;

    private ComicComponent comicComponent;
    private ComicViewModel selectedComic;

    public static Intent getCallingIntent(@NonNull Activity activity, @NonNull ComicViewModel comic) {
        final Intent intent = new Intent(activity, ComicDetailActivity.class);
        intent.putExtra(KEY_SELECTED_COMIC, comic);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedComic = getIntent().getParcelableExtra(KEY_SELECTED_COMIC);
        initDependencyInjection();
        initBaseView(getViewConfig());
    }

    private void initDependencyInjection() {
        comicComponent = DaggerComicComponent.builder()
                .appComponent(getAppComponent())
                .build();
        comicComponent.inject(this);
    }

    private ViewConfig getViewConfig() {
        return new ViewConfig(
                true,
                R.menu.comics_bottom_nav,
                this::onBottomNavItemSelectedListener,
                R.id.bottom_nav_info
        );
    }

    private boolean onBottomNavItemSelectedListener(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bottom_nav_info:
                navigator.openComicInfo(this, selectedComic);
                return true;
            case R.id.bottom_nav_reviews:
                navigator.openComicReviews(this, selectedComic);
                return true;
            case R.id.bottom_nav_images:
                navigator.openComicGallery(this, selectedComic.getImages());
                return true;
        }
        return false;
    }

    @NonNull
    @Override
    public ComicComponent getComponent() {
        return comicComponent;
    }

}