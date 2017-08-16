package com.garagu.marvel.presentation.login.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseNavigator;
import com.garagu.marvel.presentation.home.view.HomeActivity;

/**
 * Created by garagu.
 */
public class Navigator extends BaseNavigator {

    public void openLogin(@NonNull Activity activity) {
        openFragment(activity, LoginFragment.newInstance());
    }

    public void openHome(@NonNull Activity activity) {
        final Intent intent = HomeActivity.getCallingIntent(activity);
        activity.startActivity(intent);
        activity.finish();
    }

}