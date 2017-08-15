package com.garagu.marvel.presentation.comic.view;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.ComicDetailActivity;
import com.garagu.marvel.presentation.comic.view.detail.gallery.ComicGalleryFragment;
import com.garagu.marvel.presentation.comic.view.detail.gallery.ImageDetailFragment;
import com.garagu.marvel.presentation.comic.view.detail.info.ComicInfoFragment;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsFragment;
import com.garagu.marvel.presentation.comic.view.detail.reviews.NewReviewFragment;
import com.garagu.marvel.presentation.comic.view.list.ComicListFragment;
import com.garagu.marvel.presentation.common.view.BaseNavigator;

import java.util.List;

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

    public void openNewReview(@NonNull Fragment currentFragment, @NonNull ComicViewModel comic) {
        addFragment(currentFragment, NewReviewFragment.newInstance(comic));
    }

    public void openComicGallery(@NonNull Activity activity, @NonNull List<String> urls) {
        openFragment(activity, ComicGalleryFragment.newInstance(urls));
    }

    public void openImageDetail(@NonNull Fragment currentFragment, @NonNull String url) {
        addFragment(currentFragment, ImageDetailFragment.newInstance(url));
    }

}