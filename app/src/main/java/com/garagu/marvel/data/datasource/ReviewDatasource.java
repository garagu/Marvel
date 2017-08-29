package com.garagu.marvel.data.datasource;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.MyReviewEntity;
import com.garagu.marvel.data.entity.review.ReviewEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ReviewDatasource {
    Observable<Boolean> addReview(int comicId, @NonNull ReviewEntity comicReview, @NonNull String userId, @NonNull MyReviewEntity userReview);

    Observable<List<ReviewEntity>> getReviewsByComic(int comicId);

    Observable<List<MyReviewEntity>> getReviewsByUser(@NonNull String userId);
}