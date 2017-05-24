package com.garagu.marvel.presentation.application.di;

import com.garagu.marvel.presentation.common.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garagu.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity);

}