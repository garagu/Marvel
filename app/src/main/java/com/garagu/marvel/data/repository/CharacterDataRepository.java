package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.data.mapper.CharacterEntityMapper;
import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.model.common.Offset;
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
    public Observable<PaginatedCharacterList> getCharacters(Offset offset) {
        return datasource.getCharacters(offset.getOffset()).map(mapper::mapEntityToModel);
    }

}