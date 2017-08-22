package com.garagu.marvel.presentation.reviews.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.reviews.view.MyReviewsActivity;
import com.garagu.marvel.presentation.reviews.view.MyReviewsFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MyReviewsModule.class)
public interface MyReviewsComponent {
    void inject(MyReviewsActivity activity);

    void inject(MyReviewsFragment fragment);
}