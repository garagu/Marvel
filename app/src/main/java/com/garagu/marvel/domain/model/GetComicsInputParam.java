package com.garagu.marvel.domain.model;

/**
 * Created by garagu.
 */
public class GetComicsInputParam {

    private String id;
    private int offset;

    private GetComicsInputParam(String id, int offset) {
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

        public GetComicsInputParam build() {
            return new GetComicsInputParam(id, offset);
        }

    }

}