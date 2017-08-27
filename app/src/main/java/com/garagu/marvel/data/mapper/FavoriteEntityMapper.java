package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.common.FavoriteEntity;
import com.garagu.marvel.domain.model.common.Favorite;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class FavoriteEntityMapper {

    @Inject
    public FavoriteEntityMapper() {
    }

    @NonNull
    public Favorite entityToModel(@NonNull FavoriteEntity entity) {
        return new Favorite(entity.getUserId(), entity.getFavoriteId(), entity.getName(), entity.getThumbnail(), entity.getType());
    }

    @NonNull
    public FavoriteEntity modelToEntity(@NonNull Favorite model) {
        return new FavoriteEntity(model.getUserId(), model.getFavoriteId(), model.getName(), model.getThumbnail(), model.getType());
    }

}