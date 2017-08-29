package com.garagu.marvel.presentation.favorites.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;
import com.garagu.marvel.presentation.favorites.di.DaggerMyFavoritesComponent;
import com.garagu.marvel.presentation.favorites.di.MyFavoritesComponent;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class MyFavoritesActivity extends BaseActivity implements HasInjection<MyFavoritesComponent> {

    @Inject
    Navigator navigator;

    private MyFavoritesComponent component;

    @NonNull
    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, MyFavoritesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        showBackButton();
        navigator.openMyFavorites(this);
    }

    private void initDependencyInjection() {
        component = DaggerMyFavoritesComponent.builder()
                .appComponent(getAppComponent())
                .build();
        component.inject(this);
    }

    @NonNull
    @Override
    public MyFavoritesComponent getComponent() {
        return component;
    }

}