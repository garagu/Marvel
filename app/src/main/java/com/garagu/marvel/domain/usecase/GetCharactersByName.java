package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.repository.CharacterRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.GetCharactersByName.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetCharactersByName extends UseCase<InputParam, PaginatedCharacterList> {

    private final CharacterRepository repository;

    @Inject
    public GetCharactersByName(ExecutorThread executorThread, PostExecutionThread postExecutionThread, CharacterRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<PaginatedCharacterList> buildObservable(InputParam inputParam) {
        return repository.getCharactersByName(inputParam.getOffset(), inputParam.getCharacterName());
    }

    public static class InputParam {

        private final String characterName;
        private final int offset;

        public InputParam(String characterName, int offset) {
            this.characterName = characterName;
            this.offset = offset;
        }

        private String getCharacterName() {
            return characterName;
        }

        private int getOffset() {
            return offset;
        }

    }

}