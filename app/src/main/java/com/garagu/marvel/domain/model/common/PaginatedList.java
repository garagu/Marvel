package com.garagu.marvel.domain.model.common;

import java.util.List;

/**
 * Created by garagu.
 */
public class PaginatedList<T> {

    private final int count;
    private final int offset;
    private final int total;
    private final List<T> list;

    public PaginatedList(int count, int offset, int total, List<T> list) {
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

    public List<T> getList() {
        return list;
    }

}