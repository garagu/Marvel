package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.comic.PaginatedComicList;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.GetComicsByTitle.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetComicsByTitle extends UseCase<InputParam, PaginatedComicList> {

    private final ComicRepository repository;

    @Inject
    GetComicsByTitle(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ComicRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<PaginatedComicList> buildObservable(InputParam inputParam) {
        return repository.getComicsByTitle(inputParam.getTitle(), inputParam.getOffset());
    }

    public static class InputParam {

        private final String title;
        private final int offset;

        public InputParam(String title, int offset) {
            this.title = title;
            this.offset = offset;
        }

        private String getTitle() {
            return title;
        }

        private int getOffset() {
            return offset;
        }

    }

}