package com.garagu.marvel.presentation.login.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseNavigator;
import com.garagu.marvel.presentation.home.view.HomeActivity;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
    }

    public void openLogin(@NonNull Activity activity) {
        openFragment(activity, LoginFragment.newInstance());
    }

    public void openHome(@NonNull Activity activity, @NonNull UserViewModel user) {
        final Intent intent = HomeActivity.getCallingIntent(activity, user);
        activity.startActivity(intent);
        activity.finish();
    }

    public void openRegister(@NonNull Activity activity) {
        Toast.makeText(activity, R.string.message_next_version, Toast.LENGTH_SHORT).show();
        // TODO openFragment(activity, RegisterFragment.newInstance());
    }

}