package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.ResultEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicDatasource {

    Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(String id, int offset);

}