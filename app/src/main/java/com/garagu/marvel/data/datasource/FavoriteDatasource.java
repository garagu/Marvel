package com.garagu.marvel.data.datasource;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.favorite.FavoriteEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface FavoriteDatasource {
    Observable<Boolean> addFavorite(@NonNull FavoriteEntity entity);

    Observable<Boolean> deleteFavorite(@NonNull String userId, int id, int type);

    Observable<List<FavoriteEntity>> getFavorites(@NonNull String userId);

    Observable<Boolean> isFavorite(@NonNull String userId, int id, int type);
}