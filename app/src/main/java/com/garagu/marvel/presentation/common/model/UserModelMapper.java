package com.garagu.marvel.presentation.common.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.common.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by garagu.
 */
@Singleton
public class UserModelMapper {

    @Inject
    public UserModelMapper() {
    }

    @NonNull
    public UserViewModel mapUserModelToViewModel(@NonNull User model) {
        return new UserViewModel.Builder()
                .withId(model.getId())
                .withEmail(model.getEmail())
                .withName(model.getName())
                .build();
    }

}