package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.common.Review;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ReviewRepository {
    Observable<Boolean> addReviewToComic(String comicId, String userId, Review review);

    Observable<List<Review>> getReviewByComic(String comicId);

    Observable<List<Review>> getReviewsByUser(String userId);
}