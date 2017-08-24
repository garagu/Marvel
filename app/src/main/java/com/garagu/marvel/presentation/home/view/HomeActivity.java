package com.garagu.marvel.presentation.home.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HasInjection<AppComponent> {

    @Inject
    Navigator navigator;

    @NonNull
    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        navigator.openHome(this);
    }

    private void initDependencyInjection() {
        getAppComponent().inject(this);
    }

    @NonNull
    @Override
    public AppComponent getComponent() {
        return getAppComponent();
    }

}