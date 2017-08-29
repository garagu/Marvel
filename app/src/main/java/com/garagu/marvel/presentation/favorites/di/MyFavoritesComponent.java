package com.garagu.marvel.presentation.favorites.di;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.character.di.CharacterModule;
import com.garagu.marvel.presentation.favorites.view.MyFavoritesActivity;
import com.garagu.marvel.presentation.favorites.view.MyFavoritesFragment;

import dagger.Component;

/**
 * Created by garagu.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MyFavoritesModule.class, CharacterModule.class})
public interface MyFavoritesComponent {
    void inject(MyFavoritesActivity activity);

    void inject(MyFavoritesFragment fragment);
}