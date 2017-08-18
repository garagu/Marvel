package com.garagu.marvel.presentation.home.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;
import com.garagu.marvel.presentation.home.di.DaggerHomeComponent;
import com.garagu.marvel.presentation.home.di.HomeComponent;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HasInjection<HomeComponent> {

    private static final String KEY_USER = "user";

    @Inject
    Navigator navigator;

    private HomeComponent component;

    @NonNull
    public static Intent getCallingIntent(@NonNull Activity activity, @NonNull UserViewModel user) {
        final Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra(KEY_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        showHome();
    }

    private void initDependencyInjection() {
        component = DaggerHomeComponent.builder()
                .appComponent(getAppComponent())
                .build();
        component.inject(this);
    }

    private void showHome() {
        final UserViewModel user = getIntent().getParcelableExtra(KEY_USER);
        navigator.openHome(this, user);
    }

    @NonNull
    @Override
    public HomeComponent getComponent() {
        return component;
    }

}