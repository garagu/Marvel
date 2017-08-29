package com.garagu.marvel.presentation.common.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.favorite.Favorite;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class FavoriteModelMapper {

    @Inject
    public FavoriteModelMapper() {
    }

    @NonNull
    public List<FavoriteViewModel> listModelToViewModel(@NonNull List<Favorite> modelList) {
        final List<FavoriteViewModel> viewModelList = new ArrayList<>();
        for (Favorite model : modelList) {
            final FavoriteViewModel viewModel = simpleModelToViewModel(model);
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

    @NonNull
    private FavoriteViewModel simpleModelToViewModel(@NonNull Favorite model) {
        return new FavoriteViewModel.Builder()
                .withFavoriteId(model.getFavoriteId())
                .withName(model.getName())
                .withUrlThumbnail(model.getThumbnail())
                .withType(model.getType())
                .withUserId(model.getUserId())
                .build();
    }

}