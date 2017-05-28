package com.garagu.marvel.data.net.interceptor;

import android.content.Context;

import com.garagu.marvel.data.entity.Result;
import com.garagu.marvel.data.net.exception.MarvelException;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by garagu.
 */
public class ErrorInterceptor implements Interceptor {

    private Context context;

    public ErrorInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() == 409) {
            throw new MarvelException(context);
        }
        return response;
    }

}