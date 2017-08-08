package com.garagu.marvel.data.datasource.remote;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;
import com.garagu.marvel.data.net.MarvelApi;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicRemoteDatasource extends MarvelDatasource implements ComicDatasource {

    public ComicRemoteDatasource(MarvelApi api) {
        super(api);
    }

    @Override
    public Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(String id, int offset) {
        return getApi().getComicsByCharacter(id, offset, getAuthParameters());
    }

}