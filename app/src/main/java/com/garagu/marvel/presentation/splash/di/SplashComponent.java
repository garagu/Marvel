package com.garagu.marvel.presentation.splash.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.splash.view.SplashActivity;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
}