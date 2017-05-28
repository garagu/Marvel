package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.datasource.remote.ComicRemoteDatasource;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.data.repository.ComicDataRepository;
import com.garagu.marvel.data.repository.ComicRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class ComicModule {

    /*
    @Provides
    ComicDatasource provideComicDatasource(Application application) {
        return new ComicLocalDatasource(application);
    }
    */

    @Provides
    ComicDatasource provideComicDatasource(MarvelApi marvelApi) {
        return new ComicRemoteDatasource(marvelApi);
    }

    @Provides
    ComicRepository provideComicRepository(ComicDatasource datasource) {
        return new ComicDataRepository(datasource);
    }

}