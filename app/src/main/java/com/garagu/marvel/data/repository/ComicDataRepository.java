package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.ResultEntity;
import com.garagu.marvel.data.mapper.ComicEntityMapper;
import com.garagu.marvel.domain.model.ComicList;
import com.garagu.marvel.domain.repository.ComicRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicDataRepository implements ComicRepository {

    private final ComicDatasource datasource;
    private final ComicEntityMapper mapper;

    @Inject
    public ComicDataRepository(ComicDatasource datasource, ComicEntityMapper mapper) {
        this.datasource = datasource;
        this.mapper = mapper;
    }

    public Observable<ComicList> getComicsByCharacter(String id, int offset) {
        return datasource.getComicsByCharacter(id, offset).map(mapper::mapEntityToModel);
    }

}