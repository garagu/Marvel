package com.garagu.marvel.presentation.character.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.character.view.CharacterActivity;
import com.garagu.marvel.presentation.character.view.detail.CharacterDetailFragment;
import com.garagu.marvel.presentation.character.view.list.CharacterListFragment;
import com.garagu.marvel.presentation.favorites.di.MyFavoritesModule;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {CharacterModule.class, MyFavoritesModule.class})
public interface CharacterComponent {
    void inject(CharacterActivity activity);

    void inject(CharacterListFragment fragment);

    void inject(CharacterDetailFragment fragment);
}