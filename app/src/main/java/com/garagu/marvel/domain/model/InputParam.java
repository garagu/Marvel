package com.garagu.marvel.domain.model;

/**
 * Created by garagu.
 */
public class InputParam {

    private String id;
    private int offset;

    private InputParam(String id, int offset) {
        this.id = id;
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public int getOffset() {
        return offset;
    }

    public static class Builder {

        private String id;
        private int offset;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public InputParam build() {
            return new InputParam(id, offset);
        }

    }

}