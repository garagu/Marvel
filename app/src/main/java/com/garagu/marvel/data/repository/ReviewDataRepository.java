package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.entity.review.MyReviewEntity;
import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.data.mapper.ReviewEntityMapper;
import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.domain.model.review.MyReview;
import com.garagu.marvel.domain.repository.ReviewRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ReviewDataRepository implements ReviewRepository {

    private final ReviewDatasource datasource;
    private final ReviewEntityMapper mapper;

    @Inject
    public ReviewDataRepository(ReviewDatasource datasource, ReviewEntityMapper mapper) {
        this.datasource = datasource;
        this.mapper = mapper;
    }

    @Override
    public Observable<Boolean> addReviewToComic(int comicId, String userId, MyReview review) {
        final ReviewEntity reviewEntity = mapper.userReviewToComicReview(review);
        final MyReviewEntity myReviewEntity = mapper.modelToEntity(review);
        return datasource.addReview(comicId, reviewEntity, userId, myReviewEntity);
    }

    @Override
    public Observable<List<Review>> getReviewByComic(int comicId) {
        return datasource.getReviewsByComic(comicId)
                .map(mapper::listComicReviewEntityToModel);
    }

    @Override
    public Observable<List<MyReview>> getReviewsByUser(String userId) {
        return datasource.getReviewsByUser(userId)
                .map(mapper::listUserReviewEntityToModel);
    }

}