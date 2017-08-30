package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.MyReviewEntity;
import com.garagu.marvel.domain.model.review.MyReview;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class MyReviewEntityMapper {

    @Inject
    public MyReviewEntityMapper() {
    }

    @NonNull
    public List<MyReview> listEntityToModel(@NonNull List<MyReviewEntity> entityList) {
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
    public MyReviewEntity simpleModelToEntity(@NonNull MyReview model) {
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