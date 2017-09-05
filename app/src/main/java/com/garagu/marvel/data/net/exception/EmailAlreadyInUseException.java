package com.garagu.marvel.data.net.exception;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.R;

/**
 * Created by garagu.
 */
public class EmailAlreadyInUseException extends Exception {

    private final Context context;

    public EmailAlreadyInUseException(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public String getMessage() {
        return context.getString(R.string.error_email_already_in_use);
    }

}