package com.garagu.marvel.presentation.comic.view;

import android.app.Fragment;
import android.os.Bundle;

import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.comic.view.list.ListFragment;
import com.garagu.marvel.presentation.common.BaseActivity;
import com.garagu.marvel.presentation.common.HasInjection;

/**
 * Created by garagu.
 */
public class ComicActivity extends BaseActivity implements HasInjection<ComicComponent> {

    private ComicComponent comicComponent;

    @Override
    protected Fragment getInitialFragment() {
        return ListFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjector();
    }

    private void initDependencyInjector() {
        comicComponent = DaggerComicComponent.builder()
                .appComponent(getAppComponent())
                .comicModule(new ComicModule())
                .build();
    }

    @Override
    public ComicComponent getComponent() {
        return comicComponent;
    }

}