package com.garagu.marvel.presentation.auth;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseNavigator;
import com.garagu.marvel.presentation.home.view.HomeActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by garagu.
 */
@Singleton
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
    }

    public void openCreateUser(@NonNull Activity activity) {
        openFragment(activity, RegisterFragment.newInstance());
    }

    public void openHome(@NonNull Activity activity) {
        final Intent intent = HomeActivity.getCallingIntent(activity);
        activity.startActivity(intent);
        activity.finish();
    }

    public void openSignIn(@NonNull Activity activity) {
        openFragment(activity, SignInFragment.newInstance());
    }

}