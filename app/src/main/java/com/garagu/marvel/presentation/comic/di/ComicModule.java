package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.data.local.FileManager;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.datasource.local.ComicLocalDatasource;
import com.garagu.marvel.data.mapper.ComicEntityMapper;
import com.garagu.marvel.data.repository.ComicDataRepository;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.ComicModelMapper;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class ComicModule {

    @Provides
    @ActivityScope
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @ActivityScope
    ComicDatasource provideComicDatasource(FileManager fileManager, Gson gson) {
        return new ComicLocalDatasource(fileManager, gson);
    }

/*
    @Provides
    @ActivityScope
    ComicDatasource provideComicDatasource(MarvelApi marvelApi) {
        return new ComicRemoteDatasource(marvelApi);
    }
*/
    @Provides
    @ActivityScope
    ComicEntityMapper provideComicEntityMapper() {
        return new ComicEntityMapper();
    }

    @Provides
    @ActivityScope
    ComicRepository provideComicRepository(ComicDatasource datasource, ComicEntityMapper mapper) {
        return new ComicDataRepository(datasource, mapper);
    }

    @Provides
    @ActivityScope
    ComicModelMapper provideComicModelMapper() {
        return new ComicModelMapper();
    }

}