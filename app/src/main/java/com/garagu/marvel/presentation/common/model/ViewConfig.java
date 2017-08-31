package com.garagu.marvel.presentation.common.model;

import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;

/**
 * Created by garagu.
 */

public class ViewConfig {

    private final boolean showBack;
    @MenuRes
    private int bottomNavMenu;
    private OnNavigationItemSelectedListener onBottomNavItemSelectedListener;
    @IdRes
    private int bottomNavItemSelected;

    public ViewConfig(boolean showBack, int bottomNavMenu, OnNavigationItemSelectedListener onBottomNavItemSelectedListener, int bottomNavItemSelected) {
        this.showBack = showBack;
        this.bottomNavMenu = bottomNavMenu;
        this.onBottomNavItemSelectedListener = onBottomNavItemSelectedListener;
        this.bottomNavItemSelected = bottomNavItemSelected;
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