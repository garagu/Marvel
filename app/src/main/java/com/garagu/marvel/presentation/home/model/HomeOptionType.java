package com.garagu.marvel.presentation.home.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.garagu.marvel.presentation.home.model.HomeOptionType.CHARACTERS;
import static com.garagu.marvel.presentation.home.model.HomeOptionType.COMICS;
import static com.garagu.marvel.presentation.home.model.HomeOptionType.FAVORITES;
import static com.garagu.marvel.presentation.home.model.HomeOptionType.REVIEWS;

/**
 * Created by garagu.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({CHARACTERS, COMICS, FAVORITES, REVIEWS})
public @interface HomeOptionType {
    int CHARACTERS = 0;
    int COMICS = 1;
    int FAVORITES = 2;
    int REVIEWS = 3;
}