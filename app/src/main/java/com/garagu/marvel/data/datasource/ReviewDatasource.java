package com.garagu.marvel.data.datasource;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.domain.model.common.Review;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ReviewDatasource {
    Observable<List<ReviewEntity>> getReviewsByComic(@NonNull String comicId);

    Observable<Boolean> addReviewToComic(@NonNull String comicId, @NonNull ReviewEntity review);
}