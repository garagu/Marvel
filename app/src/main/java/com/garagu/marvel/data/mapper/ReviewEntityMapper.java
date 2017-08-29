package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.MyReviewEntity;
import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.domain.model.review.MyReview;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ReviewEntityMapper {

    @Inject
    public ReviewEntityMapper() {
    }

    @NonNull
    public List<Review> listComicReviewEntityToModel(@NonNull List<ReviewEntity> entityList) {
        final List<Review> modelList = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            final Review model = simpleEntityToModel(entity);
            modelList.add(model);
        }
        return modelList;
    }

    @NonNull
    private Review simpleEntityToModel(@NonNull ReviewEntity entity) {
        return new Review(
                entity.getRate(),
                entity.getText(),
                entity.getAuthor(),
                entity.getDate()
        );
    }

    @NonNull
    public List<MyReview> listUserReviewEntityToModel(@NonNull List<MyReviewEntity> entityList) {
        final List<MyReview> modelList = new ArrayList<>();
        for (MyReviewEntity entity : entityList) {
            final MyReview model = simpleEntityToModel(entity);
            modelList.add(model);
        }
        return modelList;
    }

    @NonNull
    private MyReview simpleEntityToModel(@NonNull MyReviewEntity entity) {
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

    @NonNull
    public ReviewEntity userReviewToComicReview(@NonNull MyReview model) {
        return new ReviewEntity(
                model.getRate(),
                model.getText(),
                model.getAuthor(),
                model.getDate()
        );
    }

}