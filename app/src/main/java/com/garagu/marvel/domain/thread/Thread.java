package com.garagu.marvel.domain.thread;

import io.reactivex.Scheduler;

/**
 * Created by garagu.
 */
public interface Thread {

    Scheduler getScheduler();

}