package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.auth.UserEntity;
import com.garagu.marvel.domain.model.common.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by garagu.
 */
@Singleton
public class UserEntityMapper {

    @Inject
    public UserEntityMapper() {
    }

    @NonNull
    public User mapUserEntityToModel(@NonNull UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

}