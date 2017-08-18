package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.common.User;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface LoginRepository {
    Observable<User> getUser();

    Observable<User> login(String email, String password);

    void logout();

    Observable<User> registerUser(String email, String password, String name);
}