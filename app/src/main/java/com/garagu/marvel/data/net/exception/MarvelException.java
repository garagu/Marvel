package com.garagu.marvel.data.net.exception;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.R;

import java.io.IOException;

/**
 * Created by garagu.
 */
public class MarvelException extends IOException {

    private final Context context;

    public MarvelException(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public String getMessage() {
        return context.getString(R.string.error_generic);
    }

}