package com.garagu.marvel.presentation.application.di;

import android.app.Application;

import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.presentation.common.BaseActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garagu.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(BaseActivity activity);

    Application application();

    Picasso picasso();

    MarvelApi api();

}
