package com.garagu.marvel.presentation.comic.view.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class ComicListActivity extends BaseActivity implements HasInjection<ComicComponent> {

    private static final String KEY_CHARACTER_ID = "characterId";

    @Inject
    Navigator navigator;

    private ComicComponent comicComponent;

    @NonNull
    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, ComicListActivity.class);
    }

    @NonNull
    public static Intent getCallingIntent(@NonNull Activity activity, int characterId) {
        final Intent intent = new Intent(activity, ComicListActivity.class);
        intent.putExtra(KEY_CHARACTER_ID, characterId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        showBackButton();
        final int characterId = getIntent().getIntExtra(KEY_CHARACTER_ID, CharacterViewModel.DEFAULT_ID);
        navigator.openComicList(this, characterId);
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