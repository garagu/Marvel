package com.garagu.marvel.presentation.common.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.model.FragmentTransition;

/**
 * Created by garagu.
 */
public class BaseNavigator {

    @IdRes
    private static final int CONTAINER_VIEW_ID = R.id.frame_container;

    protected void openFragment(@NonNull Activity activity, @NonNull Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(CONTAINER_VIEW_ID, fragment)
                .commit();
    }

    protected void addFragment(@NonNull Fragment currentFragment, @NonNull Fragment newFragment) {
        currentFragment.getFragmentManager()
                .beginTransaction()
                .hide(currentFragment)
                .add(CONTAINER_VIEW_ID, newFragment)
                .addToBackStack(null)
                .commit();
    }

    protected void addFragmentWithAnimation(@NonNull FragmentTransition transition) {
        setTransitionsOfNewFragment(transition.getNewFragment());
        final FragmentTransaction ft = transition.getCurrentFragment().getFragmentManager().beginTransaction();
        if ((transition.getSharedView() != null) && (transition.getName() != null)) {
            ft.addSharedElement(transition.getSharedView(), transition.getName());
        }
        ft.hide(transition.getCurrentFragment())
                .add(CONTAINER_VIEW_ID, transition.getNewFragment())
                .addToBackStack(null)
                .commit();
    }

    private void setTransitionsOfNewFragment(@NonNull Fragment newFragment) {
        newFragment.setEnterTransition(new Fade());
        final TransitionSet sharedElementTransition = new TransitionSet();
        sharedElementTransition.setOrdering(TransitionSet.ORDERING_TOGETHER);
        sharedElementTransition
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform());
        newFragment.setSharedElementEnterTransition(sharedElementTransition);
        newFragment.setSharedElementReturnTransition(sharedElementTransition);
    }

}