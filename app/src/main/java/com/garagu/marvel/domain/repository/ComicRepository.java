package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.PaginatedList;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicRepository {

    Observable<PaginatedList<Comic>> getComicsByCharacter(String id, int offset);

}