package com.garagu.marvel.presentation.character.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.di.DaggerCharacterComponent;
import com.garagu.marvel.presentation.common.BaseActivity;
import com.garagu.marvel.presentation.common.HasInjection;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by garagu.
 */
public class CharacterActivity extends BaseActivity implements HasInjection<CharacterComponent> {

    @Inject
    CharacterDatasource datasource;

    private CharacterComponent characterComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjector();
        datasource.getCharacters(0)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> Log.d("Characters", "OK"),
                        error -> Log.d("Characters", "KO")
                );
    }

    private void initDependencyInjector() {
        characterComponent = DaggerCharacterComponent.builder()
                .appComponent(getAppComponent())
                .build();
        characterComponent.inject(this);
    }

    @NonNull
    @Override
    public CharacterComponent getComponent() {
        return characterComponent;
    }

}