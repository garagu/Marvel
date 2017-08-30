package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.domain.model.review.MyReview;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ReviewRepository {
    Observable<Boolean> addReviewToComic(int comicId, String userId, MyReview review);

    Observable<List<Review>> getReviewByComic(int comicId);

    Observable<List<MyReview>> getReviewsByUser(String userId);
}