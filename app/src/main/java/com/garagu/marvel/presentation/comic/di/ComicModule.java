package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.datasource.remote.ComicRemoteDatasource;
import com.garagu.marvel.data.datasource.remote.ReviewRemoteDatasource;
import com.garagu.marvel.data.mapper.ComicDateEntityMapper;
import com.garagu.marvel.data.mapper.ComicEntityMapper;
import com.garagu.marvel.data.mapper.ReviewEntityMapper;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.data.repository.ComicDataRepository;
import com.garagu.marvel.data.repository.ReviewDataRepository;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.domain.repository.ReviewRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.ComicModelMapper;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.garagu.marvel.presentation.common.model.ReviewModelMapper;

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
    ReviewDatasource provideReviewDatasource() {
        return new ReviewRemoteDatasource();
    }

    @Provides
    @ActivityScope
    ReviewEntityMapper provideReviewEntityMapper() {
        return new ReviewEntityMapper();
    }

    @Provides
    @ActivityScope
    ReviewRepository provideReviewRepository(ReviewDatasource datasource, ReviewEntityMapper mapper) {
        return new ReviewDataRepository(datasource, mapper);
    }

    @Provides
    @ActivityScope
    ReviewModelMapper provideReviewModelMapper() {
        return new ReviewModelMapper();
    }

    /*
        @Provides
        @ActivityScope
        ComicDatasource provideComicDatasource(FileManager fileManager, Gson gson) {
            return new ComicLocalDatasource(fileManager, gson);
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
        final ComicDateEntityMapper dateMapper = new ComicDateEntityMapper();
        return new ComicEntityMapper(dateMapper);
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