package com.garagu.marvel.data.datasource;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.character.CharacterEntity;
import com.garagu.marvel.data.entity.character.CharacterListEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface CharacterDatasource {
    Observable<ResultEntity<CharacterListEntity>> getCharacters(int offset);

    Observable<ResultEntity<CharacterListEntity>> getCharactersByName(int offset, @NonNull String name);

    Observable<CharacterEntity> getCharacter(int id);
}