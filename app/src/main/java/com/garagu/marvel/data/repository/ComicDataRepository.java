package com.garagu.marvel.data.repository;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.mapper.ComicEntityMapper;
import com.garagu.marvel.domain.model.comic.PaginatedComicList;
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

    @Override
    public Observable<PaginatedComicList> getComics(int offset) {
        return datasource.getComics(offset)
                .map(mapper::mapEntityToModel);
    }

    public Observable<PaginatedComicList> getComicsByCharacter(int id, int offset) {
        return datasource.getComicsByCharacter(id, offset)
                .map(mapper::mapEntityToModel);
    }

    @Override
    public Observable<PaginatedComicList> getComicsByTitle(String title, int offset) {
        return datasource.getComicsByTitle(title, offset)
                .map(mapper::mapEntityToModel);
    }

}