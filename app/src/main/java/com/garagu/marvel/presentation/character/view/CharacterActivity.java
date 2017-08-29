package com.garagu.marvel.presentation.character.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.di.DaggerCharacterComponent;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class CharacterActivity extends BaseActivity implements HasInjection<CharacterComponent> {

    private static final String KEY_CHARACTER = "character";

    @Inject
    Navigator navigator;

    private CharacterComponent characterComponent;

    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, CharacterActivity.class);
    }

    public static Intent getCallingIntent(@NonNull Activity activity, @NonNull CharacterViewModel character) {
        final Intent intent = new Intent(activity, CharacterActivity.class);
        intent.putExtra(KEY_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        showBackButton();
        navigator.openFirstScreen(this, (getIntent() != null) ? getIntent().getParcelableExtra(KEY_CHARACTER) : null);
    }

    private void initDependencyInjection() {
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