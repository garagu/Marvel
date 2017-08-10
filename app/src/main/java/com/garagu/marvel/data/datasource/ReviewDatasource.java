package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.review.ReviewEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ReviewDatasource {
    Observable<List<ReviewEntity>> getReviewsByComic(String comicId);
}