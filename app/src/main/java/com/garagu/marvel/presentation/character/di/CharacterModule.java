package com.garagu.marvel.presentation.character.di;

import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.data.datasource.remote.CharacterRemoteDatasource;
import com.garagu.marvel.data.mapper.CharacterEntityMapper;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.data.repository.CharacterDataRepository;
import com.garagu.marvel.domain.repository.CharacterRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class CharacterModule {

    /*
    @Provides
    @ActivityScope
    CharacterDatasource provideCharacterDatasource(FileManager fileManager, Gson gson) {
        return new CharacterLocalDatasource(fileManager, gson);
    }
    */

    @Provides
    @ActivityScope
    CharacterDatasource provideCharacterDatasource(MarvelApi api) {
        return new CharacterRemoteDatasource(api);
    }

    @Provides
    @ActivityScope
    CharacterRepository provideRepository(CharacterDatasource datasource, CharacterEntityMapper mapper) {
        return new CharacterDataRepository(datasource, mapper);
    }

}