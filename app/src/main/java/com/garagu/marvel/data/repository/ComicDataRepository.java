package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.mapper.ComicMapper;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.PaginatedList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicDataRepository implements ComicRepository {

    private ComicDatasource datasource;

    @Inject
    public ComicDataRepository(ComicDatasource datasource) {
        this.datasource = datasource;
    }

    public Observable<PaginatedList<Comic>> getComicsByCharacter(String id) {
        return datasource.getComicsByCharacter(id).map(this::mapEntityToModel);
    }

    private PaginatedList<Comic> mapEntityToModel(ComicListEntity entity) {
        ComicMapper mapper = new ComicMapper();
        return mapper.mapEntityToModel(entity);
    }

}