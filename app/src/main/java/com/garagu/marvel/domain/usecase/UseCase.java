package com.garagu.marvel.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by garagu.
 */
public abstract class UseCase<I, O> {

    private Scheduler executorThread = Schedulers.newThread();
    private Scheduler postExecutionThread = AndroidSchedulers.mainThread();

    abstract Observable<O> buildObservable(I param);

    public Observable<O> execute(I param) {
        return buildObservable(param)
                .subscribeOn(executorThread)
                .observeOn(postExecutionThread);
    }

}