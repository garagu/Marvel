package com.garagu.marvel.presentation.character.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.detail.CharacterDetailFragment;
import com.garagu.marvel.presentation.character.view.list.CharacterListFragment;
import com.garagu.marvel.presentation.comic.view.list.ComicListActivity;
import com.garagu.marvel.presentation.common.model.FragmentTransition;
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

    public void openFirstScreen(@NonNull Activity activity, @Nullable CharacterViewModel character) {
        openFragment(activity, (character == null) ? CharacterListFragment.newInstance() : CharacterDetailFragment.newInstance(character));
    }

    public void openDetail(@NonNull Fragment currentFragment, @Nullable View clickedView, @NonNull CharacterViewModel character) {
        final FragmentTransition fragmentTransition = new FragmentTransition(
                currentFragment,
                CharacterDetailFragment.newInstance(character),
                clickedView,
                currentFragment.getString(R.string.transition_character_detail)
        );
        addFragmentWithAnimation(fragmentTransition);
    }

    public void openComics(@NonNull Activity activity, int characterId) {
        final Intent intent = ComicListActivity.getCallingIntent(activity, characterId);
        activity.startActivity(intent);
    }

}