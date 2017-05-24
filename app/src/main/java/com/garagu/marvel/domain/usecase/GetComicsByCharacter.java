package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.Comic;
import com.garagu.marvel.domain.repository.ComicRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */

public class GetComicsByCharacter extends UseCase<Integer, List<Comic>> {

    private ComicRepository repository;

    @Inject
    GetComicsByCharacter(ComicRepository repository) {
        this.repository = repository;
    }

    @Override
    Observable<List<Comic>> buildObservable(Integer id) {
        return repository.getComicsByCharacter(id);
    }

}