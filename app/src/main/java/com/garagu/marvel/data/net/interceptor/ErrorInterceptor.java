package com.garagu.marvel.data.net.interceptor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.data.net.exception.MarvelException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by garagu.
 */
public class ErrorInterceptor implements Interceptor {

    private final Context context;

    public ErrorInterceptor(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        final Response response = chain.proceed(request);
        if (response.code() == 409) {
            throw new MarvelException(context);
        }
        return response;
    }

}