package com.garagu.marvel.domain.model;

import java.util.List;

/**
 * Created by garagu.
 */
public class ComicList {

    private final int count;
    private final int offset;
    private final int total;
    private final List<Comic> list;

    public ComicList(int count, int offset, int total, List<Comic> list) {
        this.count = count;
        this.offset = offset;
        this.total = total;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public List<Comic> getList() {
        return list;
    }

}