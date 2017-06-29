package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.ComicList;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicRepository {

    Observable<ComicList> getComicsByCharacter(String id, int offset);

}