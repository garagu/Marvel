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
    public List<Review> listEntityToModel(@NonNull List<ReviewEntity> entityList) {
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
                entity.getTitle(),
                entity.getDate()
        );
    }

    @NonNull
    public ReviewEntity simpleModelToEntity(@NonNull Review model) {
        return new ReviewEntity(
                model.getRate(),
                model.getText(),
                model.getAuthor(),
                model.getTitle(),
                model.getDate()
        );
    }

}