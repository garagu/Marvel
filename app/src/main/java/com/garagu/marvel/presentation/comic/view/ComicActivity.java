package com.garagu.marvel.presentation.comic.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class ComicActivity extends BaseActivity implements HasInjection<ComicComponent> {

    @Inject
    Navigator navigator;

    private ComicComponent comicComponent;

    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, ComicActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        showBackButton();
        navigator.openComicList(this);
    }

    private void initDependencyInjection() {
        comicComponent = DaggerComicComponent.builder()
                .appComponent(getAppComponent())
                .comicModule(new ComicModule())
                .build();
        comicComponent.inject(this);
    }

    @NonNull
    @Override
    public ComicComponent getComponent() {
        return comicComponent;
    }

}