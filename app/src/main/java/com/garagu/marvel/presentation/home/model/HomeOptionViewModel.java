package com.garagu.marvel.presentation.home.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by garagu.
 */
public class HomeOptionViewModel {

    @HomeOptionType
    private final int type;
    @DrawableRes
    private final int bgImage;
    @StringRes
    private final int text;
    private final int textPosition;

    public HomeOptionViewModel(@HomeOptionType int type, @DrawableRes int bgImage, @StringRes int text, int textPosition) {
        this.type = type;
        this.bgImage = bgImage;
        this.text = text;
        this.textPosition = textPosition;
    }

    @HomeOptionType
    public int getType() {
        return type;
    }

    @DrawableRes
    public int getBgImage() {
        return bgImage;
    }

    @StringRes
    public int getText() {
        return text;
    }

    public int getTextPosition() {
        return textPosition;
    }

}