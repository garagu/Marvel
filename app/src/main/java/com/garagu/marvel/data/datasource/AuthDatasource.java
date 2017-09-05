package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.auth.UserEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface AuthDatasource {
    Observable<UserEntity> getUser();

    Observable<UserEntity> googleSignIn(String email, String token);

    Observable<UserEntity> signIn(String email, String password);

    Observable<Boolean> signOut();

    Observable<UserEntity> createUser(String name, String email, String password);
}