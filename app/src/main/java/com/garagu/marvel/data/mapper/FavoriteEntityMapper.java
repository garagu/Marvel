package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.common.FavoriteEntity;
import com.garagu.marvel.domain.model.common.Favorite;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

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
    public List<Favorite> listEntityToModel(@NonNull List<FavoriteEntity> entityList) {
        final List<Favorite> modelList = new ArrayList<>();
        for (FavoriteEntity entity : entityList) {
            final Favorite model = simpleEntityToModel(entity);
            modelList.add(model);
        }
        return modelList;
    }

    @NonNull
    public Favorite simpleEntityToModel(@NonNull FavoriteEntity entity) {
        return new Favorite(entity.getUserId(), entity.getFavoriteId(), entity.getName(), entity.getThumbnail(), entity.getType());
    }

    @NonNull
    public FavoriteEntity modelToEntity(@NonNull Favorite model) {
        return new FavoriteEntity(model.getUserId(), model.getFavoriteId(), model.getName(), model.getThumbnail(), model.getType());
    }

}