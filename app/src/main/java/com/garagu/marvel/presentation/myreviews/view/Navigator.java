package com.garagu.marvel.presentation.myreviews.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.ComicDetailActivity;
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

    public void openComicDetail(@NonNull Activity activity, @NonNull ComicViewModel comic) {
        final Intent intent = ComicDetailActivity.getCallingIntent(activity, comic);
        activity.startActivity(intent);
    }

}