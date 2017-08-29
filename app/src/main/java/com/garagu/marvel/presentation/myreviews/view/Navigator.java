package com.garagu.marvel.presentation.myreviews.view;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.di.ActivityScope;
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

    public void openMyReviews(@NonNull Activity activity) {
        openFragment(activity, MyReviewsFragment.newInstance());
    }

}