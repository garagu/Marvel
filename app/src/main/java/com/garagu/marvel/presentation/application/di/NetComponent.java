package com.garagu.marvel.presentation.application.di;

import android.app.Application;

import com.garagu.marvel.presentation.common.BaseActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by garagu.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(BaseActivity activity);

    Application application();
    Picasso picasso();
    OkHttpClient okHttpClient();
    Retrofit retrofit();

}
