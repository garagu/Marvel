package com.garagu.marvel.data.datasource.remote;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.net.MarvelApi;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by garagu.
 */
class MarvelDatasource {

    private MarvelApi api;
    private final Map<String, String> authParameters = new HashMap<>();

    MarvelDatasource(MarvelApi api) {
        this.api = api;
        initAuthParameters();
    }

    private void initAuthParameters() {
        authParameters.put("ts", "1");
        authParameters.put("apikey", BuildConfig.MARVEL_API_KEY);
        authParameters.put("hash", BuildConfig.MARVEL_API_HASH);
    }

    MarvelApi getApi() {
        return api;
    }

    Map<String, String> getAuthParameters() {
        return authParameters;
    }

}