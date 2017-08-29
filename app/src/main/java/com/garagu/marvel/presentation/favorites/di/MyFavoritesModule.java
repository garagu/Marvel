package com.garagu.marvel.presentation.favorites.di;

import com.garagu.marvel.data.datasource.FavoriteDatasource;
import com.garagu.marvel.data.datasource.local.FavoriteLocalDatasource;
import com.garagu.marvel.data.local.FileManager;
import com.garagu.marvel.data.mapper.FavoriteEntityMapper;
import com.garagu.marvel.data.repository.FavoriteDataRepository;
import com.garagu.marvel.domain.repository.FavoriteRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class MyFavoritesModule {

    @Provides
    @ActivityScope
    FavoriteDatasource provideFavoritesDatasource(FileManager fileManager, Gson gson) {
        return new FavoriteLocalDatasource(fileManager, gson);
    }

    @Provides
    @ActivityScope
    FavoriteRepository provideFavoritesRepository(FavoriteDatasource datasource, FavoriteEntityMapper mapper) {
        return new FavoriteDataRepository(datasource, mapper);
    }

}