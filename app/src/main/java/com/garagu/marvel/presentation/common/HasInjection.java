package com.garagu.marvel.presentation.common;

import android.support.annotation.NonNull;

/**
 * Created by garagu.
 */
public interface HasInjection<T> {
    @NonNull
    T getComponent();
}