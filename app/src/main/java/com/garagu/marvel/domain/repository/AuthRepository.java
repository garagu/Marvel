package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.common.User;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface AuthRepository {
    Observable<User> createUser(String email, String password, String name);

    Observable<User> getUser();

    Observable<User> googleSignIn(String email, String token);

    Observable<User> signIn(String email, String password);

    Observable<Boolean> signOut();
}