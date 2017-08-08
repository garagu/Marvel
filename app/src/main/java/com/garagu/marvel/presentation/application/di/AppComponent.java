package com.garagu.marvel.presentation.application.di;

import android.app.Application;

import com.garagu.marvel.data.local.FileManager;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garagu.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    Application application();

    FileManager fileManager();

    Gson gson();

    Picasso picasso();

    ExecutorThread executorThread();

    PostExecutionThread postExecutionThread();

    MarvelApi api();

}
