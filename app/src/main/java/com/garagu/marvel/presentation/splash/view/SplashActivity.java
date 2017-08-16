package com.garagu.marvel.presentation.splash.view;

import android.content.Intent;
import android.os.Bundle;

import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.login.view.LoginActivity;

/**
 * Created by garagu.
 */
public class SplashActivity extends BaseActivity {

    // TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = LoginActivity.getCallingIntent(this);
        startActivity(intent);
        finish();
    }

}