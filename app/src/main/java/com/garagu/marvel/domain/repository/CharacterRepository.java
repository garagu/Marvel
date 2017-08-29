package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.character.PaginatedCharacterList;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface CharacterRepository {
    Observable<PaginatedCharacterList> getCharacters(int offset);

    Observable<Character> getCharacter(int id);
}