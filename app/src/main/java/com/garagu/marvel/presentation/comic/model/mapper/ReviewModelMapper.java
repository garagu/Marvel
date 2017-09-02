package com.garagu.marvel.presentation.comic.model.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ReviewModelMapper extends ModelToViewModelMapper<Review, ReviewViewModel> {

    @Inject
    public ReviewModelMapper() {
    }

    @NonNull
    @Override
    public ReviewViewModel simpleModelToViewModel(@NonNull Review model) {
        return new ReviewViewModel.Builder()
                .withAuthor(model.getAuthor())
                .withDate(model.getDate())
                .withRate(model.getRate())
                .withText(model.getText())
                .build();
    }

}