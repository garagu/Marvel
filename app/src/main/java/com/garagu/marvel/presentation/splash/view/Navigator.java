package com.garagu.marvel.presentation.splash.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseNavigator;
import com.garagu.marvel.presentation.home.view.HomeActivity;
import com.garagu.marvel.presentation.login.view.LoginActivity;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
    }

    void openHome(@NonNull Activity activity, @NonNull UserViewModel user) {
        final Intent intent = HomeActivity.getCallingIntent(activity, user);
        activity.startActivity(intent);
        activity.finish();
    }

    void openLogin(@NonNull Activity activity) {
        final Intent intent = LoginActivity.getCallingIntent(activity);
        activity.startActivity(intent);
        activity.finish();
    }

}