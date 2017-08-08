package com.garagu.marvel.domain.model.common;

/**
 * Created by garagu.
 */
public class Offset {

    public static final Offset DEFAULT = new Offset.Builder().withOffset(0).build();

    private final int offset;

    private Offset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public static class Builder {

        private int offset;

        public Builder withOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public Offset build() {
            return new Offset(offset);
        }

    }

}