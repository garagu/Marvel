package com.garagu.marvel.presentation.application.di;

import android.content.Context;

import com.garagu.marvel.data.local.FileManager;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.domain.repository.AuthRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.presentation.auth.AuthActivity;
import com.garagu.marvel.presentation.auth.RegisterFragment;
import com.garagu.marvel.presentation.auth.SignInFragment;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.home.view.HomeActivity;
import com.garagu.marvel.presentation.home.view.HomeFragment;
import com.garagu.marvel.presentation.splash.SplashActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by garagu.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class, AuthModule.class})
public interface AppComponent {
    Context context();

    FileManager fileManager();

    Gson gson();

    ExecutorThread executorThread();

    PostExecutionThread postExecutionThread();

    ImageLoader imageLoader();

    MarvelApi api();

    DatabaseReference dbReference();

    AuthRepository authRepository();

    void inject(SplashActivity activity);

    void inject(AuthActivity activity);

    void inject(SignInFragment fragment);

    void inject(RegisterFragment fragment);

    void inject(HomeActivity activity);

    void inject(HomeFragment fragment);
}