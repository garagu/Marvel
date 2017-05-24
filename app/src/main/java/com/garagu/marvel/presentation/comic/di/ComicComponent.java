package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.comic.view.list.ListFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(modules = ComicModule.class, dependencies = AppComponent.class)
public interface ComicComponent extends AppComponent {

    void inject(ListFragment fragment);

}