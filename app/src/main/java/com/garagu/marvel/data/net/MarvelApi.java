package com.garagu.marvel.data.net;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.data.entity.character.CharacterListEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;

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

    @GET("comics?orderBy=title")
    Observable<ResultEntity<ComicListEntity>> getComics(
            @Query("offset") int offset,
            @QueryMap Map<String, String> authParameters);

    @GET("comics")
    Observable<ResultEntity<ComicListEntity>> getComicsByTitle(
            @Query("title") String title,
            @Query("offset") int offset,
            @QueryMap Map<String, String> authParameters);

    @GET("characters/{id}/comics?orderBy=title")
    Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(
            @Path("id") int id,
            @Query("offset") int offset,
            @QueryMap Map<String, String> authParameters);

    @GET("characters")
    Observable<ResultEntity<CharacterListEntity>> getCharacters(
            @Query("offset") int offset,
            @QueryMap Map<String, String> authParameters);

}