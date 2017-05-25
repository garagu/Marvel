package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.data.repository.ComicRepository;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.PaginatedList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */

public class GetComicsByCharacter extends UseCase<String, PaginatedList<Comic>> {

    private ComicRepository repository;

    @Inject
    GetComicsByCharacter(ComicRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<PaginatedList<Comic>> buildObservable(String id) {
        return repository.getComicsByCharacter(id);
    }

}