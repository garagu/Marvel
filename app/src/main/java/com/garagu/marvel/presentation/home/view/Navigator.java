package com.garagu.marvel.presentation.home.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.character.view.CharacterActivity;
import com.garagu.marvel.presentation.comic.view.list.ComicListActivity;
import com.garagu.marvel.presentation.common.view.BaseNavigator;

/**
 * Created by garagu.
 */
public class Navigator extends BaseNavigator {

    void openHome(@NonNull Activity activity) {
        openFragment(activity, HomeFragment.newInstance());
    }

    void openCharacters(@NonNull Activity activity) {
        final Intent intent = CharacterActivity.getCallingIntent(activity);
        activity.startActivity(intent);
    }

    void openComics(@NonNull Activity activity) {
        final Intent intent = ComicListActivity.getCallingIntent(activity);
        activity.startActivity(intent);
    }

}