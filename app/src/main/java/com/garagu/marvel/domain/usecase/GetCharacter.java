package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.repository.CharacterRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetCharacter extends UseCase<Integer, Character> {

    private final CharacterRepository repository;

    @Inject
    public GetCharacter(ExecutorThread executorThread, PostExecutionThread postExecutionThread, CharacterRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Character> buildObservable(Integer id) {
        return repository.getCharacter(id);
    }

}