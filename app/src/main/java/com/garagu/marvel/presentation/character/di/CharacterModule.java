package com.garagu.marvel.presentation.character.di;

import com.garagu.marvel.data.FileManager;
import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.data.datasource.local.CharacterLocalDatasource;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class CharacterModule {

    @Provides
    @ActivityScope
    CharacterDatasource provideCharacterDatasource(FileManager fileManager, Gson gson) {
        return new CharacterLocalDatasource(fileManager, gson);
    }

}