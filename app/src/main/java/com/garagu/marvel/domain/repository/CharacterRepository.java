package com.garagu.marvel.domain.repository;

import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.model.common.Offset;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public interface CharacterRepository {

    Observable<PaginatedCharacterList> getCharacters(Offset offset);

}