package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.datasource.remote.ComicRemoteDatasource;
import com.garagu.marvel.data.mapper.ComicEntityMapper;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.data.repository.ComicDataRepository;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.ComicModelMapper;

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
    @ActivityScope
    ComicDatasource provideComicDatasource(MarvelApi marvelApi) {
        return new ComicRemoteDatasource(marvelApi);
    }

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