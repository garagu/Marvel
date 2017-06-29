package com.garagu.marvel.presentation.application.di;

import android.app.Application;

import com.garagu.marvel.data.BackgroundThread;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.presentation.application.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    ExecutorThread provideExecutorThread() {
        return new BackgroundThread();
    }

    @Singleton
    @Provides
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

}