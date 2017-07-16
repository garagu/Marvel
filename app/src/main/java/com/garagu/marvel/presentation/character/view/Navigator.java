package com.garagu.marvel.presentation.character.view;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.character.view.list.CharacterListFragment;
import com.garagu.marvel.presentation.common.view.BaseNavigator;

/**
 * Created by garagu.
 */
public class Navigator extends BaseNavigator {

    void openListOfCharacters(@NonNull Activity activity) {
        openFragment(activity, CharacterListFragment.newInstance());
    }

}