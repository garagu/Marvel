package com.garagu.marvel.presentation.splash.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.splash.di.DaggerSplashComponent;
import com.garagu.marvel.presentation.splash.di.SplashComponent;
import com.garagu.marvel.presentation.splash.view.SplashPresenter.SplashView;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter presenter;
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();
    }

    private void initDependencyInjection() {
        final SplashComponent component = DaggerSplashComponent.builder()
                .appComponent(getAppComponent())
                .build();
        component.inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @Override
    public void openHome(@NonNull UserViewModel user) {
        navigator.openHome(this, user);
    }

    @Override
    public void openLogin() {
        navigator.openLogin(this);
    }

}