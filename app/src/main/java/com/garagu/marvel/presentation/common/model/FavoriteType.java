package com.garagu.marvel.presentation.common.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.garagu.marvel.presentation.common.model.FavoriteType.CHARACTER;
import static com.garagu.marvel.presentation.common.model.FavoriteType.COMIC;

/**
 * Created by garagu.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({CHARACTER, COMIC})
public @interface FavoriteType {
    int CHARACTER = 0;
    int COMIC = 1;
}