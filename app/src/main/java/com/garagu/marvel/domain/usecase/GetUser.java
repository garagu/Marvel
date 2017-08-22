package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.repository.AuthRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */

public class GetUser extends UseCase<Void, User> {

    private final AuthRepository repository;

    @Inject
    public GetUser(ExecutorThread executorThread, PostExecutionThread postExecutionThread, AuthRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<User> buildObservable(Void input) {
        return repository.getUser();
    }

}