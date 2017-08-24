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
    public Observable<ResultEntity<ComicListEntity>> getComics(int offset) {
        return getApi().getComics(offset, getAuthParameters());
    }

    @Override
    public Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(int id, int offset) {
        return getApi().getComicsByCharacter(id, offset, getAuthParameters());
    }

    @Override
    public Observable<ResultEntity<ComicListEntity>> getComicsByTitle(String title, int offset) {
        return getApi().getComicsByTitle(title, offset, getAuthParameters());
    }

}