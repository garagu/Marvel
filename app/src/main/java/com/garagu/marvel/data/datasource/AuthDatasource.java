package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.common.UserEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface AuthDatasource {
    Observable<UserEntity> getUser();

    Observable<UserEntity> signIn(String email, String password);

    void signOut();

    Observable<UserEntity> createUser(String name, String email, String password);
}