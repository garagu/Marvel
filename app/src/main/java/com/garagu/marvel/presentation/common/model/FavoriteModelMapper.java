package com.garagu.marvel.presentation.common.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.favorite.Favorite;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class FavoriteModelMapper extends ModelToViewModelMapper<Favorite, FavoriteViewModel> {

    @Inject
    public FavoriteModelMapper() {
    }

    @NonNull
    @Override
    public FavoriteViewModel simpleModelToViewModel(@NonNull Favorite model) {
        return new FavoriteViewModel.Builder()
                .withFavoriteId(model.getFavoriteId())
                .withName(model.getName())
                .withUrlThumbnail(model.getThumbnail())
                .withType(model.getType())
                .withUserId(model.getUserId())
                .build();
    }

}