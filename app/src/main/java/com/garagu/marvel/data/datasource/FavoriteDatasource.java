package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.common.FavoriteEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface FavoriteDatasource {
    Observable<List<FavoriteEntity>> getFavorites(String userId);

    Observable<Boolean> isFavorite(String userId, int id, int type);

    void setFavorite(FavoriteEntity entity);
}