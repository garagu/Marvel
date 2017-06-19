package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.data.repository.ComicRepository;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.GetComicsInputParam;
import com.garagu.marvel.domain.model.PaginatedList;
import com.garagu.marvel.domain.thread.BackgroundThread;
import com.garagu.marvel.domain.thread.UIThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetComicsByCharacter extends UseCase<GetComicsInputParam, PaginatedList<Comic>> {

    private ComicRepository repository;

    @Inject
    GetComicsByCharacter(BackgroundThread backgroundThread, UIThread uiThread, ComicRepository repository) {
        super(backgroundThread, uiThread);
        this.repository = repository;
    }

    @Override
    protected Observable<PaginatedList<Comic>> buildObservable(GetComicsInputParam param) {
        return repository.getComicsByCharacter(param.getId(), param.getOffset());
    }

}