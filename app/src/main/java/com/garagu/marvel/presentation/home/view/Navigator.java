package com.garagu.marvel.presentation.home.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.auth.AuthActivity;
import com.garagu.marvel.presentation.character.view.CharacterActivity;
import com.garagu.marvel.presentation.comic.view.list.ComicListActivity;
import com.garagu.marvel.presentation.common.view.BaseNavigator;
import com.garagu.marvel.presentation.favorites.view.MyFavoritesActivity;
import com.garagu.marvel.presentation.myreviews.view.MyReviewsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by garagu.
 */
@Singleton
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
    }

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

    void openMyFavorites(@NonNull Activity activity) {
        final Intent intent = MyFavoritesActivity.getCallingIntent(activity);
        activity.startActivity(intent);
    }

    void openMyReviews(@NonNull Activity activity) {
        final Intent intent = MyReviewsActivity.getCallingIntent(activity);
        activity.startActivity(intent);
    }

    void openSignIn(@NonNull Activity activity) {
        final Intent intent = AuthActivity.getCallingIntent(activity);
        activity.startActivity(intent);
        activity.finish();
    }

}