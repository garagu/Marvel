package com.garagu.marvel.data.datasource.remote;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.ResultEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.garagu.marvel.data.net.MarvelApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicRemoteDatasource implements ComicDatasource {

    private final MarvelApi api;
    private final Map<String, String> authParameters = new HashMap<>();

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
    public Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(String id, int offset) {
        return api.getComicsByCharacter(id, offset, authParameters);
    }

}