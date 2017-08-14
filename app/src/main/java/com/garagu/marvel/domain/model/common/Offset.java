package com.garagu.marvel.domain.model.common;

/**
 * Created by garagu.
 */
public class Offset {

    public static final Offset DEFAULT = new Offset(0);

    private final int offset;

    public Offset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

}