package com.garagu.marvel.data;

import com.garagu.marvel.domain.thread.ExecutorThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by garagu.
 */
public class BackgroundThread implements ExecutorThread {

    @Override
    public Scheduler getScheduler() {
        return Schedulers.newThread();
    }

}