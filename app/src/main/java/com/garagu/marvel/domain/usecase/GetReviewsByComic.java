package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.domain.repository.ReviewRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetReviewsByComic extends UseCase<Integer, List<Review>> {

    private final ReviewRepository repository;

    @Inject
    public GetReviewsByComic(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ReviewRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Review>> buildObservable(Integer comicId) {
        return repository.getReviewByComic(comicId);
    }

}