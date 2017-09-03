package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ReviewEntityMapper extends EntityToModelMapper<ReviewEntity, Review> {

    @Inject
    public ReviewEntityMapper() {
    }

    @NonNull
    @Override
    public Review simpleEntityToModel(@NonNull ReviewEntity entity) {
        return new Review(
                entity.getRate(),
                entity.getText(),
                entity.getAuthor(),
                entity.getDate()
        );
    }

}