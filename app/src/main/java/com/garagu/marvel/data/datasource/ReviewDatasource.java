package com.garagu.marvel.data.datasource;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.ReviewEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ReviewDatasource {
    Observable<Boolean> addReviewToComic(@NonNull String comicId, @NonNull String userId, @NonNull ReviewEntity review);

    Observable<List<ReviewEntity>> getReviewsByComic(@NonNull String comicId);

    Observable<List<ReviewEntity>> getReviewsByUser(@NonNull String userId);
}