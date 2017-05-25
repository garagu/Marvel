package com.garagu.marvel.data.repository;

import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.PaginatedList;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicRepository {

    // TODO offset
    Observable<PaginatedList<Comic>> getComicsByCharacter(String id);

}