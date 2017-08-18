package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.common.UserEntity;
import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class UserEntityMapper {

    @Inject
    public UserEntityMapper() {
    }

    @NonNull
    public User mapUserEntityToModel(@NonNull UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail());
    }

}