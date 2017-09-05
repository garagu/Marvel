package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.repository.AuthRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.GoogleSignIn.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GoogleSignIn extends UseCase<InputParam, User> {

    private final AuthRepository repository;

    @Inject
    public GoogleSignIn(ExecutorThread executorThread, PostExecutionThread postExecutionThread, AuthRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<User> buildObservable(InputParam inputParam) {
        return repository.googleSignIn(inputParam.getEmail(), inputParam.getToken());
    }

    public static class InputParam {

        private final String email;
        private final String token;

        public InputParam(String email, String token) {
            this.email = email;
            this.token = token;
        }

        String getEmail() {
            return email;
        }

        String getToken() {
            return token;
        }

    }

}