package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.comic.ComicList;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicRepository {
    Observable<ComicList> getComics(int offset);

    Observable<ComicList> getComicsByCharacter(String id, int offset);
}