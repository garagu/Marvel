package com.garagu.marvel.data.net.interceptor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.data.net.NetworkUtils;
import com.garagu.marvel.data.net.exception.ConnectionException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by garagu.
 */
public class OfflineCacheInterceptor implements Interceptor {

    private final Context context;

    public OfflineCacheInterceptor(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isOnline(context)) {
            final CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(1, TimeUnit.DAYS)
                    .build();
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }
        try {
            return chain.proceed(request);
        } catch (UnknownHostException e) {
            throw new ConnectionException(context);
        }
    }

}