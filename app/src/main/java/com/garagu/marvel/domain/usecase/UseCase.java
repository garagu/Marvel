package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.data.BackgroundThread;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.presentation.application.UIThread;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

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