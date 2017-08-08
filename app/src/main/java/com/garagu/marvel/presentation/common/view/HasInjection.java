package com.garagu.marvel.presentation.common.view;

import android.support.annotation.NonNull;

/**
 * Created by garagu.
 */
public interface HasInjection<T> {
    @NonNull
    T getComponent();
}