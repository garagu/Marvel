package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.data.repository.ComicDataRepository;
import com.garagu.marvel.data.repository.ComicRepository;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.datasource.remote.ComicRemoteDatasource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class ComicModule {

    @Provides
    ComicRepository provideComicRepository() {
        ComicDatasource datasource = new ComicRemoteDatasource();
        return new ComicDataRepository(datasource);
    }

}