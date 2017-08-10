package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.character.CharacterListEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface CharacterDatasource {
    Observable<ResultEntity<CharacterListEntity>> getCharacters(int offset);
}