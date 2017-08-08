package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.comic.view.ComicActivity;
import com.garagu.marvel.presentation.comic.view.detail.DetailFragment;
import com.garagu.marvel.presentation.comic.view.list.ListFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ComicModule.class)
public interface ComicComponent {

    void inject(ComicActivity activity);

    void inject(ListFragment fragment);

    void inject(DetailFragment fragment);

}