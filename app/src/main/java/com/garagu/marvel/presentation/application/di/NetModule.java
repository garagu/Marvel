package com.garagu.marvel.presentation.application.di;

import android.app.Application;
import android.util.Log;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.net.ApiConnection;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.data.BackgroundThread;
import com.garagu.marvel.presentation.application.UIThread;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    Picasso providePicasso(Application application) {
        Picasso picasso = new Picasso.Builder(application)
                .listener((p, uri, e) -> {
                    if (BuildConfig.DEBUG) {
                        Log.e("Picasso", e.getMessage());
                    }
                })
                .build();
        if (BuildConfig.DEBUG) {
            picasso.setIndicatorsEnabled(true);
        }
        return picasso;
    }

    @Singleton
    @Provides
    MarvelApi provideApi(Application application) {
        return new ApiConnection(application).getServices(MarvelApi.class, MarvelApi.BASE_URL);
    }

}