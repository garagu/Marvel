package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.MyReviewEntity;
import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.domain.model.review.MyReview;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class MyReviewEntityMapper extends EntityToModelMapper<MyReviewEntity, MyReview> {

    @Inject
    public MyReviewEntityMapper() {
    }

    @NonNull
    @Override
    public MyReview simpleEntityToModel(@NonNull MyReviewEntity entity) {
        return new MyReview(
                entity.getRate(),
                entity.getText(),
                entity.getAuthor(),
                entity.getComicId(),
                entity.getComicTitle(),
                entity.getDate()
        );
    }

    @NonNull
    public ReviewEntity userReviewToComicReview(@NonNull MyReview model) {
        return new ReviewEntity(
                model.getRate(),
                model.getText(),
                model.getAuthor(),
                model.getDate()
        );
    }

    @NonNull
    public MyReviewEntity modelToEntity(@NonNull MyReview model) {
        return new MyReviewEntity(
                model.getRate(),
                model.getText(),
                model.getAuthor(),
                model.getComicId(),
                model.getComicTitle(),
                model.getDate()
        );
    }

}