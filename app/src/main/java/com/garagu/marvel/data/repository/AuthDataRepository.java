package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.AuthDatasource;
import com.garagu.marvel.data.mapper.UserEntityMapper;
import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.repository.AuthRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class AuthDataRepository implements AuthRepository {

    private final AuthDatasource datasource;
    private final UserEntityMapper mapper;

    @Inject
    public AuthDataRepository(AuthDatasource datasource, UserEntityMapper mapper) {
        this.datasource = datasource;
        this.mapper = mapper;
    }

    @Override
    public Observable<User> createUser(String name, String email, String password) {
        return datasource.createUser(name, email, password).map(mapper::mapUserEntityToModel);
    }

    @Override
    public Observable<User> getUser() {
        return datasource.getUser().map(mapper::mapUserEntityToModel);
    }

    @Override
    public Observable<User> signIn(String email, String password) {
        return datasource.signIn(email, password).map(mapper::mapUserEntityToModel);
    }

    @Override
    public void signOut() {
        datasource.signOut();
    }

}