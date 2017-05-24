package com.garagu.marvel.presentation.application;

import android.app.Application;

import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.application.di.AppModule;
import com.garagu.marvel.presentation.application.di.DaggerAppComponent;

/**
 * Created by garagu.
 */
public class MarvelApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjector();
    }

    private void initDependencyInjector() {
        appComponent =  DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}