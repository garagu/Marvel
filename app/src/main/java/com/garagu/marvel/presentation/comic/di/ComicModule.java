package com.garagu.marvel.presentation.comic.di;

import android.app.Application;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.datasource.local.ComicLocalDatasource;
import com.garagu.marvel.data.datasource.remote.ComicRemoteDatasource;
import com.garagu.marvel.data.repository.ComicDataRepository;
import com.garagu.marvel.data.repository.ComicRepository;

import dagger.Module;
import dagger.Provides;
/**
 * Created by garagu.
 */
@Module
public class ComicModule {

    @Provides
    ComicDatasource provideComicDatasource(Application application) {
        return new ComicLocalDatasource(application);
    }

    /*
    @Provides
    ComicDatasource provideComicDatasource(Application application) {
        return new ComicRemoteDatasource();
    }
    */

    @Provides
    ComicRepository provideComicRepository(ComicDatasource datasource) {
        return new ComicDataRepository(datasource);
    }

}