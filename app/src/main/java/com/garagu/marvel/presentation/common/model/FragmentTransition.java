package com.garagu.marvel.presentation.common.model;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by garagu.
 */
public class FragmentTransition {

    private final Fragment currentFragment;
    private final Fragment newFragment;
    private final View sharedView;
    private final String name;

    public FragmentTransition(@NonNull Fragment currentFragment, @NonNull Fragment newFragment, @Nullable View sharedView, @Nullable String name) {
        this.currentFragment = currentFragment;
        this.newFragment = newFragment;
        this.sharedView = sharedView;
        this.name = name;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public Fragment getNewFragment() {
        return newFragment;
    }

    public View getSharedView() {
        return sharedView;
    }

    public String getName() {
        return name;
    }

}