package com.garagu.marvel.presentation.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.garagu.marvel.presentation.application.MarvelApplication;
import com.garagu.marvel.presentation.splash.SplashPresenter.SplashView;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject
    SplashPresenter presenter;
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersiveMode();
        initDependencyInjection();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();
    }

    private void setImmersiveMode() {
        final int uiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        getWindow().getDecorView().setSystemUiVisibility(uiVisibility);
    }

    private void initDependencyInjection() {
        ((MarvelApplication) getApplication()).getAppComponent().inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @Override
    public void openHome() {
        new Handler().postDelayed(() -> navigator.openHome(this), 2000);
    }

    @Override
    public void openAuth() {
        new Handler().postDelayed(() -> navigator.openAuth(this), 2000);
    }

}