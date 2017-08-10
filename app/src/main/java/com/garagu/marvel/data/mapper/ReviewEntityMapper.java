package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.domain.model.common.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class ReviewEntityMapper {

    @NonNull
    public List<Review> mapEntityToModel(List<ReviewEntity> entityList) {
        final List<Review> modelList = new ArrayList<>();
        for (ReviewEntity entity : entityList) {
            final Review model = mapReview(entity);
            modelList.add(model);
        }
        return modelList;
    }

    @NonNull
    private Review mapReview(ReviewEntity entity) {
        return new Review(
                entity.getRate(),
                entity.getText(),
                entity.getAuthor(),
                entity.getTitle(),
                entity.getDate()
        );
    }

}