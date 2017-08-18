package com.garagu.marvel.presentation.login.di;

import android.app.Application;

import com.garagu.marvel.data.datasource.LoginDatasource;
import com.garagu.marvel.data.datasource.remote.LoginRemoteDatasource;
import com.garagu.marvel.data.mapper.UserEntityMapper;
import com.garagu.marvel.data.repository.LoginDataRepository;
import com.garagu.marvel.domain.repository.LoginRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.login.view.Navigator;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class LoginModule {

    @Provides
    @ActivityScope
    LoginDatasource provideLoginDatasource(Application application, FirebaseAuth firebaseAuth) {
        return new LoginRemoteDatasource(application, firebaseAuth);
    }

    @Provides
    @ActivityScope
    LoginRepository provideLoginRepository(LoginDatasource datasource, UserEntityMapper mapper) {
        return new LoginDataRepository(datasource, mapper);
    }

}