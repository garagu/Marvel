package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.FavoriteDatasource;
import com.garagu.marvel.data.mapper.FavoriteEntityMapper;
import com.garagu.marvel.domain.model.favorite.Favorite;
import com.garagu.marvel.domain.repository.FavoriteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class FavoriteDataRepository implements FavoriteRepository {

    private final FavoriteDatasource datasource;
    private final FavoriteEntityMapper mapper;

    @Inject
    public FavoriteDataRepository(FavoriteDatasource datasource, FavoriteEntityMapper mapper) {
        this.datasource = datasource;
        this.mapper = mapper;
    }

    @Override
    public Observable<Boolean> deleteFavorite(String userId, int id, int type) {
        return datasource.deleteFavorite(userId, id, type);
    }

    @Override
    public Observable<List<Favorite>> getFavorites(String userId) {
        return datasource.getFavorites(userId)
                .map(mapper::listEntityToModel);
    }

    @Override
    public Observable<Boolean> isFavorite(String userId, int id, int type) {
        return datasource.isFavorite(userId, id, type);
    }

    @Override
    public Observable<Boolean> addFavorite(Favorite favorite) {
        return Observable.just(favorite)
                .map(mapper::modelToEntity)
                .flatMap(datasource::addFavorite);
    }

}