package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.comic.view.detail.ComicDetailActivity;
import com.garagu.marvel.presentation.comic.view.detail.gallery.ComicGalleryFragment;
import com.garagu.marvel.presentation.comic.view.detail.gallery.ImageDetailFragment;
import com.garagu.marvel.presentation.comic.view.detail.info.ComicInfoFragment;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsFragment;
import com.garagu.marvel.presentation.comic.view.detail.reviews.NewReviewFragment;
import com.garagu.marvel.presentation.comic.view.list.ComicListActivity;
import com.garagu.marvel.presentation.comic.view.list.ComicListFragment;
import com.garagu.marvel.presentation.reviews.di.MyReviewsModule;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ComicModule.class, MyReviewsModule.class})
public interface ComicComponent {
    void inject(ComicListActivity activity);

    void inject(ComicListFragment fragment);

    void inject(ComicDetailActivity activity);

    void inject(ComicInfoFragment fragment);

    void inject(ComicReviewsFragment fragment);

    void inject(NewReviewFragment fragment);

    void inject(ComicGalleryFragment fragment);

    void inject(ImageDetailFragment fragment);
}