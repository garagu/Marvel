package com.garagu.marvel.presentation.application.di;

import android.content.Context;

import com.garagu.marvel.data.datasource.AuthDatasource;
import com.garagu.marvel.data.datasource.remote.AuthRemoteDatasource;
import com.garagu.marvel.data.mapper.UserEntityMapper;
import com.garagu.marvel.data.repository.AuthDataRepository;
import com.garagu.marvel.domain.repository.AuthRepository;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class AuthModule {

    @Provides
    @Singleton
    AuthDatasource provideAuthDatasource(Context context, FirebaseAuth firebaseAuth) {
        return new AuthRemoteDatasource(context, firebaseAuth);
    }

    @Provides
    @Singleton
    AuthRepository provideAuthRepository(AuthDatasource datasource, UserEntityMapper mapper) {
        return new AuthDataRepository(datasource, mapper);
    }

}