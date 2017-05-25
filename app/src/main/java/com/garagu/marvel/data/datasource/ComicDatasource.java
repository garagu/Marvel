package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.ComicListEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicDatasource {

    Observable<ComicListEntity> getComicsByCharacter(String id);

}