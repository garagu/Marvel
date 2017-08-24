package com.garagu.marvel.presentation.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;

import javax.inject.Inject;

public class AuthActivity extends BaseActivity implements HasInjection<AppComponent> {

    @Inject
    Navigator navigator;

    private AppComponent component;

    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, AuthActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        hideNavigationView();
        navigator.openSignIn(this);
    }

    private void initDependencyInjection() {
        component = getAppComponent();
        component.inject(this);
    }

    @NonNull
    @Override
    public AppComponent getComponent() {
        return component;
    }

}