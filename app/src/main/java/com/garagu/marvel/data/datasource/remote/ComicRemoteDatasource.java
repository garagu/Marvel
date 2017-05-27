package com.garagu.marvel.data.datasource.remote;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.Result;
import com.garagu.marvel.data.net.MarvelApi;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicRemoteDatasource implements ComicDatasource {

    private MarvelApi api;
    private Map<String, String> authParameters = new HashMap<>();

    @Inject
    public ComicRemoteDatasource(MarvelApi api) {
        this.api = api;
        initAuthParameters();
    }

    private void initAuthParameters() {
        authParameters.put("ts", "1");
        authParameters.put("apikey", BuildConfig.MARVEL_API_KEY);
        authParameters.put("hash", BuildConfig.MARVEL_API_HASH);
    }

    @Override
    public Observable<Result<ComicListEntity>> getComicsByCharacter(String id) {
        return api.getComicsByCharacter(id, authParameters);
    }

}