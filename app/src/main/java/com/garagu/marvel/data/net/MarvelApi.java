package com.garagu.marvel.data.net;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.Result;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by garagu.
 */
public interface MarvelApi {

    String BASE_URL = BuildConfig.MARVEL_API_URL;

    @GET("characters/{id}/comics?orderBy=title")
    Observable<Result<ComicListEntity>> getComicsByCharacter(
            @Path("id") String id,
            @Query("offset") int offset,
            @QueryMap Map<String, String> authParameters);

}