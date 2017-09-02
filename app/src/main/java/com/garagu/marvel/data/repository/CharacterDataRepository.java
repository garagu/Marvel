package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.data.mapper.CharacterEntityMapper;
import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.repository.CharacterRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class CharacterDataRepository implements CharacterRepository {

    private final CharacterDatasource datasource;
    private final CharacterEntityMapper mapper;

    @Inject
    public CharacterDataRepository(CharacterDatasource datasource, CharacterEntityMapper mapper) {
        this.datasource = datasource;
        this.mapper = mapper;
    }

    @Override
    public Observable<PaginatedCharacterList> getCharacters(int offset) {
        return datasource.getCharacters(offset)
                .map(mapper::listEntityToModel);
    }

    @Override
    public Observable<PaginatedCharacterList> getCharactersByName(int offset, String name) {
        return datasource.getCharactersByName(offset, name)
                .map(mapper::listEntityToModel);
    }

    @Override
    public Observable<Character> getCharacter(int id) {
        return datasource.getCharacter(id)
                .map(mapper::simpleEntityToModel);
    }

}