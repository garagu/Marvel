package com.garagu.marvel.presentation.myreviews.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.myreviews.view.MyReviewsActivity;
import com.garagu.marvel.presentation.myreviews.view.MyReviewsFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MyReviewsModule.class, ComicModule.class})
public interface MyReviewsComponent {
    void inject(MyReviewsActivity activity);

    void inject(MyReviewsFragment fragment);
}