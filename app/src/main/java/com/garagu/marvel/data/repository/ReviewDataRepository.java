package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.entity.review.MyReviewEntity;
import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.data.mapper.MyReviewEntityMapper;
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
    private final ReviewEntityMapper comicReviewMapper;
    private final MyReviewEntityMapper userReviewMapper;

    @Inject
    public ReviewDataRepository(ReviewDatasource datasource, ReviewEntityMapper comicReviewMapper, MyReviewEntityMapper userReviewMapper) {
        this.datasource = datasource;
        this.comicReviewMapper = comicReviewMapper;
        this.userReviewMapper = userReviewMapper;
    }

    @Override
    public Observable<Boolean> addReviewToComic(int comicId, String userId, MyReview review) {
        final ReviewEntity reviewEntity = userReviewMapper.userReviewToComicReview(review);
        final MyReviewEntity myReviewEntity = userReviewMapper.modelToEntity(review);
        return datasource.addReview(comicId, reviewEntity, userId, myReviewEntity);
    }

    @Override
    public Observable<Boolean> checkIfUserHasReviewed(String userId, int comicId) {
        return datasource.checkIfUserHasReviewed(userId, comicId);
    }

    @Override
    public Observable<List<Review>> getReviewByComic(int comicId) {
        return datasource.getReviewsByComic(comicId)
                .map(comicReviewMapper::listEntityToModel);
    }

    @Override
    public Observable<List<MyReview>> getReviewsByUser(String userId) {
        return datasource.getReviewsByUser(userId)
                .map(userReviewMapper::listEntityToModel);
    }

}