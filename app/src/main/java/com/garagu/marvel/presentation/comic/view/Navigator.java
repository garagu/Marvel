package com.garagu.marvel.presentation.comic.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.ComicDetailActivity;
import com.garagu.marvel.presentation.comic.view.detail.info.ComicInfoFragment;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsFragment;
import com.garagu.marvel.presentation.comic.view.list.ComicListFragment;
import com.garagu.marvel.presentation.common.view.BaseNavigator;

/**
 * Created by garagu.
 */
public class Navigator extends BaseNavigator {

    public void openComicList(@NonNull Activity activity) {
        openFragment(activity, ComicListFragment.newInstance());
    }

    public void openComicDetail(@NonNull Activity activity, @NonNull ComicViewModel comic) {
        final Intent intent = ComicDetailActivity.getCallingIntent(activity, comic);
        activity.startActivity(intent);
    }

    public void openComicInfo(@NonNull Activity activity, @NonNull ComicViewModel comic) {
        openFragment(activity, ComicInfoFragment.newInstance(comic));
    }

    public void openComicReviews(@NonNull Activity activity, @NonNull ComicViewModel comic) {
        openFragment(activity, ComicReviewsFragment.newInstance(comic));
    }

}