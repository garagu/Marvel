package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.repository.ReviewRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.CheckIfUserHasReviewed.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class CheckIfUserHasReviewed extends UseCase<InputParam, Boolean> {

    private final ReviewRepository repository;

    @Inject
    public CheckIfUserHasReviewed(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ReviewRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> buildObservable(InputParam inputParam) {
        return repository.checkIfUserHasReviewed(inputParam.getUserId(), inputParam.getComicId());
    }

    public static class InputParam {

        private final String userId;
        private final int comicId;

        public InputParam(String userId, int comicId) {
            this.userId = userId;
            this.comicId = comicId;
        }

        public String getUserId() {
            return userId;
        }

        public int getComicId() {
            return comicId;
        }

    }

}