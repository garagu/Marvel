package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.NetComponent;
import com.garagu.marvel.presentation.comic.view.list.ListFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = NetComponent.class, modules = ComicModule.class)
public interface ComicComponent {

    void inject(ListFragment fragment);

}