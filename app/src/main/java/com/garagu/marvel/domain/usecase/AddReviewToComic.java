package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.common.Review;
import com.garagu.marvel.domain.repository.ReviewRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.AddReviewToComic.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */

public class AddReviewToComic extends UseCase<InputParam, Boolean> {

    private final ReviewRepository repository;

    @Inject
    public AddReviewToComic(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ReviewRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> buildObservable(InputParam inputParam) {
        return repository.addReviewToComic(inputParam.getComicId(), inputParam.getReview());
    }

    public static class InputParam {

        private String comicId;
        private Review review;

        public InputParam(String comicId, Review review) {
            this.comicId = comicId;
            this.review = review;
        }

        public String getComicId() {
            return comicId;
        }

        public Review getReview() {
            return review;
        }

    }

}