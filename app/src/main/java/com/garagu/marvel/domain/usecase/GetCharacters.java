package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.repository.CharacterRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetCharacters extends UseCase<Integer, PaginatedCharacterList> {

    private final CharacterRepository repository;

    @Inject
    public GetCharacters(ExecutorThread executorThread, PostExecutionThread postExecutionThread, CharacterRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<PaginatedCharacterList> buildObservable(Integer offset) {
        return repository.getCharacters(offset);
    }

}