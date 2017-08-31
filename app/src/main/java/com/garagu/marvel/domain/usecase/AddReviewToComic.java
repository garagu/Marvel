package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.review.MyReview;
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
        return repository.addReviewToComic(inputParam.getComicId(), inputParam.getUserId(), inputParam.getReview());
    }

    public static class InputParam {

        private final int comicId;
        private final String userId;
        private final MyReview review;

        public InputParam(int comicId, String userId, MyReview review) {
            this.comicId = comicId;
            this.userId = userId;
            this.review = review;
        }

        public int getComicId() {
            return comicId;
        }

        public String getUserId() {
            return userId;
        }

        public MyReview getReview() {
            return review;
        }

    }

}