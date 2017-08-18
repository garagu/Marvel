package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.repository.LoginRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.RegisterUser.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class RegisterUser extends UseCase<InputParam, User> {

    private final LoginRepository repository;

    @Inject
    public RegisterUser(ExecutorThread executorThread, PostExecutionThread postExecutionThread, LoginRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<User> buildObservable(InputParam input) {
        return repository.registerUser(input.getName(), input.getEmail(), input.getPassword());
    }

    public static class InputParam {

        private final String name;
        private final String email;
        private final String password;

        public InputParam(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        String getName() {
            return name;
        }

        String getEmail() {
            return email;
        }

        String getPassword() {
            return password;
        }

    }

}