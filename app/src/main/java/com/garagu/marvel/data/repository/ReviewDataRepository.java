package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.mapper.ReviewEntityMapper;
import com.garagu.marvel.domain.model.common.Review;
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
    public Observable<List<Review>> getReviewByComic(String comicId) {
        return datasource.getReviewsByComic(comicId).map(mapper::mapEntityToModel);
    }

}