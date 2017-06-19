package com.garagu.marvel.domain.thread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by garagu.
 */
public class BackgroundThread implements Thread {

    @Inject
    public BackgroundThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.newThread();
    }

}