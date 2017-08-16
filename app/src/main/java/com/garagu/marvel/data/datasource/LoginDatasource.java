package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.login.LoginEntity;
import com.garagu.marvel.data.entity.login.RegisterEntity;
import com.garagu.marvel.data.entity.login.UserEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface LoginDatasource {
    Observable<UserEntity> getUser();

    Observable<Boolean> login(LoginEntity login);

    Observable<Boolean> registerUser(RegisterEntity user);
}