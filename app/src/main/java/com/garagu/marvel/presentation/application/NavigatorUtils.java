package com.garagu.marvel.presentation.application;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;

import com.garagu.marvel.R;

/**
 * Created by garagu.
 */
public class NavigatorUtils {

    public static void openFragment(@NonNull Activity activity, @NonNull Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, fragment)
                .commit();
    }

    public static void addFragment(@NonNull Activity activity, @NonNull Fragment currentFragment, @NonNull Fragment newfragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .hide(currentFragment)
                .add(R.id.frame_container, newfragment)
                .addToBackStack(null)
                .commit();
    }

}