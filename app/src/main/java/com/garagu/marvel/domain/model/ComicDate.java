package com.garagu.marvel.domain.model;

/**
 * Created by garagu.
 */
public class ComicDate {

    private String type;
    private String releaseDate;

    private ComicDate(String type, String releaseDate) {
        this.type = type;
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public static class Builder {

        private String type;
        private String releaseDate;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public ComicDate build() {
            return new ComicDate(type, releaseDate);
        }

    }

}