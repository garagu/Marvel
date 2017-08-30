package com.garagu.marvel.data.datasource;

import com.garagu.marvel.data.entity.comic.ComicEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicDatasource {
    Observable<ComicEntity> getComic(int comicId);

    Observable<ResultEntity<ComicListEntity>> getComics(int offset);

    Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(int id, int offset);

    Observable<ResultEntity<ComicListEntity>> getComicsByTitle(String title, int offset);
}