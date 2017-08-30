package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.comic.Comic;
import com.garagu.marvel.domain.model.comic.PaginatedComicList;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface ComicRepository {
    Observable<Comic> getComic(int comicId);

    Observable<PaginatedComicList> getComics(int offset);

    Observable<PaginatedComicList> getComicsByCharacter(int id, int offset);

    Observable<PaginatedComicList> getComicsByTitle(String title, int offset);
}