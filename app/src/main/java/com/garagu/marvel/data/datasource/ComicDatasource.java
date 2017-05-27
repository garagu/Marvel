package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.Result;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicDatasource {

    Observable<Result<ComicListEntity>> getComicsByCharacter(String id);

}