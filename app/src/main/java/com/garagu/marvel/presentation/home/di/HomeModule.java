package com.garagu.marvel.presentation.home.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.home.view.Navigator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class HomeModule {

    @Provides
    @ActivityScope
    Navigator provideNavigator() {
        return new Navigator();
    }

}