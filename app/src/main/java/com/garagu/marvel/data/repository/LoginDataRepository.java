package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.LoginDatasource;
import com.garagu.marvel.data.mapper.UserEntityMapper;
import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class LoginDataRepository implements LoginRepository {

    private final LoginDatasource datasource;
    private final UserEntityMapper mapper;

    @Inject
    public LoginDataRepository(LoginDatasource datasource, UserEntityMapper mapper) {
        this.datasource = datasource;
        this.mapper = mapper;
    }

    @Override
    public Observable<User> getUser() {
        return datasource.getUser().map(mapper::mapUserEntityToModel);
    }

    @Override
    public Observable<User> login(String email, String password) {
        return datasource.login(email, password).map(mapper::mapUserEntityToModel);
    }

    @Override
    public void logout() {
        datasource.logout();
    }

    @Override
    public Observable<Boolean> registerUser(String name, String email, String password) {
        return datasource.registerUser(name, email, password);
    }

}