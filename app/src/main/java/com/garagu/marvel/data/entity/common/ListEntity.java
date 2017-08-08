package com.garagu.marvel.data.entity.common;

/**
 * Created by garagu.
 */
public class ListEntity<T> {

    private int offset;
    private int limit;
    private int total;
    private int count;
    private T[] results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public T[] getResults() {
        return results;
    }

}