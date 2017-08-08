package com.garagu.marvel.presentation.character.view;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.detail.CharacterDetailFragment;
import com.garagu.marvel.presentation.character.view.list.CharacterListFragment;
import com.garagu.marvel.presentation.common.model.FragmentTransition;
import com.garagu.marvel.presentation.common.view.BaseNavigator;

/**
 * Created by garagu.
 */
public class Navigator extends BaseNavigator {

    public void openList(@NonNull Activity activity) {
        openFragment(activity, CharacterListFragment.newInstance());
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

}