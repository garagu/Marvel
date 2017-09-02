package com.garagu.marvel.presentation.common.model;

import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;

/**
 * Created by garagu.
 */

public class ViewConfig {

    @StringRes
    private final int toolbarTitle;
    private final boolean showBack;
    @MenuRes
    private int bottomNavMenu;
    private OnNavigationItemSelectedListener onBottomNavItemSelectedListener;
    @IdRes
    private int bottomNavItemSelected;

    public ViewConfig(@StringRes int toolbarTitle, boolean showBack, int bottomNavMenu, OnNavigationItemSelectedListener onBottomNavItemSelectedListener, int bottomNavItemSelected) {
        this.toolbarTitle = toolbarTitle;
        this.showBack = showBack;
        this.bottomNavMenu = bottomNavMenu;
        this.onBottomNavItemSelectedListener = onBottomNavItemSelectedListener;
        this.bottomNavItemSelected = bottomNavItemSelected;
    }

    @StringRes
    public int getToolbarTitle() {
        return toolbarTitle;
    }

    public boolean showBack() {
        return showBack;
    }

    @MenuRes
    public int getBottomNavMenu() {
        return bottomNavMenu;
    }

    public OnNavigationItemSelectedListener getOnBottomNavItemSelectedListener() {
        return onBottomNavItemSelectedListener;
    }

    @IdRes
    public int getBottomNavItemSelected() {
        return bottomNavItemSelected;
    }

}