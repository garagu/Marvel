package com.garagu.marvel.presentation.application;

import android.app.Application;

import com.garagu.marvel.presentation.application.di.AppModule;
import com.garagu.marvel.presentation.application.di.DaggerNetComponent;
import com.garagu.marvel.presentation.application.di.NetComponent;
import com.garagu.marvel.presentation.application.di.NetModule;

/**
 * Created by garagu.
 */
public class MarvelApplication extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjector();
    }

    private void initDependencyInjector() {
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

}