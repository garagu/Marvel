package com.garagu.marvel.presentation.home.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.home.view.HomeActivity;
import com.garagu.marvel.presentation.home.view.HomeFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
    void inject(HomeFragment fragment);
}