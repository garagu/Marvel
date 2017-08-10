package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.comic.ComicList;
import com.garagu.marvel.domain.repository.ComicRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.GetComicsByCharacter.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetComicsByCharacter extends UseCase<InputParam, ComicList> {

    private final ComicRepository repository;

    @Inject
    GetComicsByCharacter(ExecutorThread executorThread, PostExecutionThread postExecutionThread, ComicRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<ComicList> buildObservable(InputParam param) {
        return repository.getComicsByCharacter(param.getCharacterId(), param.getOffset());
    }

    public static class InputParam {

        private final String characterId;
        private final int offset;

        public InputParam(String characterId, int offset) {
            this.characterId = characterId;
            this.offset = offset;
        }

        private String getCharacterId() {
            return characterId;
        }

        private int getOffset() {
            return offset;
        }

    }

}