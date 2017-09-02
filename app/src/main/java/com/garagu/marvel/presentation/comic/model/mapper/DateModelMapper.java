package com.garagu.marvel.presentation.comic.model.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.comic.ComicDate;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.DateViewModel;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class DateModelMapper extends ModelToViewModelMapper<ComicDate, DateViewModel> {

    @Inject
    public DateModelMapper() {
    }

    @NonNull
    @Override
    public DateViewModel simpleModelToViewModel(@NonNull ComicDate model) {
        return new DateViewModel.Builder()
                .withDate(model.getDate())
                .withType(model.getType())
                .build();
    }

}