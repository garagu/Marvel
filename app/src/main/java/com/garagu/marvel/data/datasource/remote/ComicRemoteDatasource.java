package com.garagu.marvel.data.datasource.remote;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.net.ApiConnection;
import com.garagu.marvel.data.net.MarvelApi;
import com.garagu.marvel.data.datasource.ComicDatasource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by garagu.
 */
public class ComicRemoteDatasource implements ComicDatasource {

    private MarvelApi api;
    private Map<String, String> authParameters = new HashMap<>();

    public ComicRemoteDatasource() {
        initApi();
    }

    private void initApi() {
        api = ApiConnection.getServices(MarvelApi.class, MarvelApi.BASE_URL);
        authParameters.put("ts", "1");
        authParameters.put("apikey", BuildConfig.MARVEL_API_KEY);
        authParameters.put("hash", BuildConfig.MARVEL_API_HASH);
    }

    @Override
    public Observable<ComicListEntity> getComicsByCharacter(String id) {
        return api.getComicsByCharacter(id, authParameters);
    }

}