package com.garagu.marvel.presentation.application.di;

import android.content.Context;
import android.util.Log;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.net.ApiConnection;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.common.view.PicassoImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Context context) {
        final Picasso picasso = new Picasso.Builder(context)
                .listener((p, uri, e) -> {
                    if (BuildConfig.DEBUG) {
                        Log.e("Picasso", e.getMessage());
                    }
                })
                .build();
//        if (BuildConfig.DEBUG) {
//            picasso.setIndicatorsEnabled(true);
//        }
        return new PicassoImpl(picasso);
    }

    @Provides
    @Singleton
    MarvelApi provideApi(Context context) {
        return new ApiConnection(context).getServices(MarvelApi.class, MarvelApi.BASE_URL);
    }

    @Provides
    @Singleton
    FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    DatabaseReference provideFirebaseDBReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

}