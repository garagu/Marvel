package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.favorite.Favorite;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface FavoriteRepository {
    Observable<Boolean> deleteFavorite(String userId, int id, int type);

    Observable<List<Favorite>> getFavorites(String userId);

    Observable<Boolean> isFavorite(String userId, int id, int type);

    Observable<Boolean> addFavorite(Favorite favorite);
}
