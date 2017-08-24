package com.garagu.marvel.domain.model.comic;

import com.garagu.marvel.domain.model.common.PaginatedList;

import java.util.List;

/**
 * Created by garagu.
 */
public class PaginatedComicList extends PaginatedList<Comic> {

    public PaginatedComicList(int count, int offset, int total, List<Comic> list) {
        super(count, offset, total, list);
    }

}