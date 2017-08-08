package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.model.common.Offset;
import com.garagu.marvel.domain.repository.CharacterRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetCharacters extends UseCase<Offset, PaginatedCharacterList> {

    private final CharacterRepository repository;

    @Inject
    public GetCharacters(ExecutorThread executorThread, PostExecutionThread postExecutionThread, CharacterRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<PaginatedCharacterList> buildObservable(Offset inputParam) {
        return repository.getCharacters(inputParam);
    }

}