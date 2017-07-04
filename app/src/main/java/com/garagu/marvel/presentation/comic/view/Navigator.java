package com.garagu.marvel.presentation.comic.view;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.NavigatorUtils;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.DetailFragment;
import com.garagu.marvel.presentation.comic.view.list.ListFragment;

/**
 * Created by garagu.
 */
public class Navigator {

    public void openComicList(@NonNull Activity activity) {
        NavigatorUtils.openFragment(activity, ListFragment.newInstance());
    }

    public void openComicDetail(@NonNull Activity activity, @NonNull Fragment fragment, @NonNull ComicViewModel comic) {
        NavigatorUtils.addFragment(activity, fragment, DetailFragment.newInstance(comic));
    }

}