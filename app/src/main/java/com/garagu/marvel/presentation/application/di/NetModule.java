package com.garagu.marvel.presentation.application.di;

import android.app.Application;

import com.garagu.marvel.BuildConfig;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
                        e.printStackTrace();
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
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MARVEL_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}