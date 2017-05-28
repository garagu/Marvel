package com.garagu.marvel.data.entity;

/**
 * Created by garagu.
 */
public class ComicListEntity {

    private int offset;
    private int limit;
    private int total;
    private int count;
    private ComicEntity[] results;

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

    public ComicEntity[] getResults() {
        return results;
    }

}