package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.comic.view.detail.ComicDetailActivity;
import com.garagu.marvel.presentation.comic.view.detail.info.ComicInfoFragment;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsFragment;
import com.garagu.marvel.presentation.comic.view.list.ComicListActivity;
import com.garagu.marvel.presentation.comic.view.list.ComicListFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ComicModule.class)
public interface ComicComponent {

    void inject(ComicListActivity activity);

    void inject(ComicListFragment fragment);

    void inject(ComicDetailActivity activity);

    void inject(ComicInfoFragment fragment);

    void inject(ComicReviewsFragment fragment);

}