package com.garagu.marvel.data.datasource.remote;

import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.character.CharacterListEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;
import com.garagu.marvel.data.net.MarvelApi;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class CharacterRemoteDatasource extends MarvelDatasource implements CharacterDatasource {

    public CharacterRemoteDatasource(MarvelApi api) {
        super(api);
    }

    @Override
    public Observable<ResultEntity<CharacterListEntity>> getCharacters(int offset) {
        return getApi().getCharacters(offset, getAuthParameters());
    }

}