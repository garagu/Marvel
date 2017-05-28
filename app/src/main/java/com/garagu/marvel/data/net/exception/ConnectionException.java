package com.garagu.marvel.data.net.exception;

import android.content.Context;

import com.garagu.marvel.R;

import java.io.IOException;

/**
 * Created by garagu.
 */
public class ConnectionException extends IOException {

    private Context context;

    public ConnectionException(Context context) {
        this.context = context;
    }

    @Override
    public String getMessage() {
        return context.getString(R.string.error_connection);
    }

}