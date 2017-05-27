package com.garagu.marvel.data.net.interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by garagu.
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(5, TimeUnit.MINUTES)
                .build();
        return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
    }

}