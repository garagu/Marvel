package com.garagu.marvel.presentation.application.di;

import android.content.Context;

import com.garagu.marvel.presentation.application.MarvelApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class AppModule {

    private final MarvelApplication application;

    public AppModule(MarvelApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return application.getApplicationContext();
    }

}