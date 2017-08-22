package com.garagu.marvel.presentation.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseActivity;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class SplashActivity extends BaseActivity implements SplashPresenter.SplashView {

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
        getAppComponent().inject(this);
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
    public void openAuth() {
        navigator.openAuth(this);
    }

}