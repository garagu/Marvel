package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.data.repository.ComicRepository;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.InputParam;
import com.garagu.marvel.domain.model.PaginatedList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetComicsByCharacter extends UseCase<InputParam, PaginatedList<Comic>> {

    private ComicRepository repository;

    @Inject
    GetComicsByCharacter(ComicRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<PaginatedList<Comic>> buildObservable(InputParam inputParam) {
        return repository.getComicsByCharacter(inputParam.getId(), inputParam.getOffset());
    }

}