package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.common.ResultEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicDatasource {

    Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(String id, int offset);

}