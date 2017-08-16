package com.garagu.marvel.presentation.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;
import com.garagu.marvel.presentation.login.di.DaggerLoginComponent;
import com.garagu.marvel.presentation.login.di.LoginComponent;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements HasInjection<LoginComponent> {

    @Inject
    Navigator navigator;

    private LoginComponent component;

    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        hideMenu();
        navigator.openLogin(this);
    }

    private void initDependencyInjection() {
        component = DaggerLoginComponent.builder()
                .appComponent(getAppComponent())
                .build();
        component.inject(this);
    }

    @NonNull
    @Override
    public LoginComponent getComponent() {
        return component;
    }

}