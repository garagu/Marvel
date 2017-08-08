package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
abstract class UseCase<I, O> {

    private final ExecutorThread executorThread;
    private final PostExecutionThread postExecutionThread;

    UseCase(ExecutorThread executorThread, PostExecutionThread postExecutionThread) {
        this.executorThread = executorThread;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<O> buildObservable(I param);

    public Observable<O> execute(I param) {
        return buildObservable(param)
                .subscribeOn(executorThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

}