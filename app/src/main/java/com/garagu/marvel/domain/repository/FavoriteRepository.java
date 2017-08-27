package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.common.Favorite;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface FavoriteRepository {
    Observable<List<Favorite>> getFavorites(String userId);

    Observable<Boolean> isFavorite(String userId, int id, int type);

    void setFavorite(Favorite favorite);
}
