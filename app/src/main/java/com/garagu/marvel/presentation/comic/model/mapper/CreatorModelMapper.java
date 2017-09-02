package com.garagu.marvel.presentation.comic.model.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.comic.Creator;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.CreatorViewModel;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class CreatorModelMapper extends ModelToViewModelMapper<Creator, CreatorViewModel> {

    @Inject
    public CreatorModelMapper() {
    }

    @NonNull
    @Override
    public CreatorViewModel simpleModelToViewModel(@NonNull Creator model) {
        final String role = mapRole(model.getRole());
        return new CreatorViewModel.Builder()
                .withName(model.getName())
                .withRole(role)
                .build();
    }

    @NonNull
    private String mapRole(@NonNull String role) {
        return role.isEmpty() ? role : Character.toUpperCase(role.charAt(0)) + role.substring(1);
    }

}