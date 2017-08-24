package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.repository.AuthRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class SignOut extends UseCase<Void, Boolean> {

    private final AuthRepository repository;

    @Inject
    public SignOut(ExecutorThread executorThread, PostExecutionThread postExecutionThread, AuthRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> buildObservable(Void param) {
        return repository.signOut();
    }

}