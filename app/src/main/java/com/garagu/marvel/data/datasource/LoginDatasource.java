package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.common.UserEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface LoginDatasource {
    Observable<UserEntity> getUser();

    Observable<UserEntity> login(String email, String password);

    void logout();

    Observable<UserEntity> registerUser(String name, String email, String password);
}