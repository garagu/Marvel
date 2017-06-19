package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.thread.BackgroundThread;
import com.garagu.marvel.domain.thread.UIThread;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by garagu.
 */
abstract class UseCase<I, O> {

    private Scheduler executorThread;
    private Scheduler postExecutionThread;

    UseCase(BackgroundThread backgroundThread, UIThread uiThread) {
        executorThread = backgroundThread.getScheduler();
        postExecutionThread = uiThread.getScheduler();
    }

    protected abstract Observable<O> buildObservable(I param);

    public Observable<O> execute(I param) {
        return buildObservable(param)
                .subscribeOn(executorThread)
                .observeOn(postExecutionThread);
    }

}