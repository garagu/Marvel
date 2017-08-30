package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.comic.Comic;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetComic extends UseCase<Integer, Comic> {

    private final ComicRepository repository;

    @Inject
    GetComic(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ComicRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Comic> buildObservable(Integer comicId) {
        return repository.getComic(comicId);
    }

}