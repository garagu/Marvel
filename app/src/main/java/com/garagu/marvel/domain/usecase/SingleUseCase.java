package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import io.reactivex.Single;

/**
 * Created by garagu.
 */
abstract class SingleUseCase<I, O> {

    private final ExecutorThread executorThread;
    private final PostExecutionThread postExecutionThread;

    SingleUseCase(ExecutorThread executorThread, PostExecutionThread postExecutionThread) {
        this.executorThread = executorThread;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Single<O> build(I param);

    public Single<O> execute(I param) {
        return build(param)
                .subscribeOn(executorThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

}