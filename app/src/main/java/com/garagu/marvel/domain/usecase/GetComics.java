package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.comic.ComicList;
import com.garagu.marvel.domain.model.common.Offset;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetComics extends UseCase<Offset, ComicList> {

    private final ComicRepository repository;

    @Inject
    GetComics(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ComicRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<ComicList> buildObservable(Offset offset) {
        return repository.getComics(offset.getOffset());
    }

}