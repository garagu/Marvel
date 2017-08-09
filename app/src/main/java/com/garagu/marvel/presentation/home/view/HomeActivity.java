package com.garagu.marvel.presentation.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;
import com.garagu.marvel.presentation.home.di.DaggerHomeComponent;
import com.garagu.marvel.presentation.home.di.HomeComponent;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements HasInjection<HomeComponent> {

    @Inject
    Navigator navigator;

    private HomeComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        navigator.openHome(this);
    }

    private void initDependencyInjection() {
        component = DaggerHomeComponent.builder()
                .appComponent(getAppComponent())
                .build();
        component.inject(this);
    }

    @NonNull
    @Override
    public HomeComponent getComponent() {
        return component;
    }

}