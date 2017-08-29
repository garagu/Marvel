package com.garagu.marvel.presentation.favorites.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.CharacterActivity;
import com.garagu.marvel.presentation.common.view.BaseNavigator;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
    }

    public void openCharacter(@NonNull Activity activity, @NonNull CharacterViewModel character) {
        final Intent intent = CharacterActivity.getCallingIntent(activity, character);
        activity.startActivity(intent);
    }

    public void openMyFavorites(@NonNull Activity activity) {
        openFragment(activity, MyFavoritesFragment.newInstance());
    }

}