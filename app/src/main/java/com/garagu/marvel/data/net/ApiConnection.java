package com.garagu.marvel.data.net;

import android.content.Context;

import com.garagu.marvel.data.net.interceptor.CacheInterceptor;
import com.garagu.marvel.data.net.interceptor.ErrorInterceptor;
import com.garagu.marvel.data.net.interceptor.OfflineCacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by garagu.
 */
public class ApiConnection {

    private static final int TIMEOUT = 20;
    private static final long MAX_CACHE_SIZE = 10 * 1024 * 1024; // 10 MB

    private Context context;

    public ApiConnection(Context context) {
        this.context = context;
    }

    public <T> T getServices(Class<T> api, String baseUrl) {
        return initRetrofit(baseUrl).create(api);
    }

    private Retrofit initRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(initClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                .cache(initCache())
                //.addInterceptor(initLogInterceptor())
                .addInterceptor(new OfflineCacheInterceptor(context))
                .addNetworkInterceptor(new CacheInterceptor())
                .addNetworkInterceptor(new ErrorInterceptor(context))
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private Cache initCache() {
        File cacheDir = new File(context.getCacheDir(), "cache");
        return new Cache(cacheDir, MAX_CACHE_SIZE);
    }

    private HttpLoggingInterceptor initLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}