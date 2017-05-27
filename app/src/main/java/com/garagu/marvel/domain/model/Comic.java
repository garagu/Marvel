package com.garagu.marvel.domain.model;

import java.util.List;

/**
 * Created by garagu.
 */
public class Comic {

    private String title;
    private String description;
    private int pages;
    private List<ComicDate> dates;
    private String seriesTitle;
    private List<ComicCreator> creators;
    private List<String> characters;
    private String isbn;
    private String format;
    private String urlThumbnail;

    private Comic(String title, String description, int pages, List<ComicDate> dates, String seriesTitle, List<ComicCreator> creators, List<String> characters, String isbn, String format, String urlThumbnail) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.dates = dates;
        this.seriesTitle = seriesTitle;
        this.creators = creators;
        this.characters = characters;
        this.isbn = isbn;
        this.format = format;
        this.urlThumbnail = urlThumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPages() {
        return pages;
    }

    public List<ComicDate> getDates() {
        return dates;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public List<ComicCreator> getCreators() {
        return creators;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getFormat() {
        return format;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public static class Builder {

        private String title;
        private String description;
        private int pages;
        private List<ComicDate> dates;
        private String seriesTitle;
        private List<ComicCreator> creators;
        private List<String> characters;
        private String isbn;
        private String format;
        private String urlThumbnail;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withPages(int pages) {
            this.pages = pages;
            return this;
        }

        public Builder withDates(List<ComicDate> dates) {
            this.dates = dates;
            return this;
        }

        public Builder withSeriesTitle(String seriesTitle) {
            this.seriesTitle = seriesTitle;
            return this;
        }

        public Builder withCreators(List<ComicCreator> creators) {
            this.creators = creators;
            return this;
        }

        public Builder withCharacters(List<String> characters) {
            this.characters = characters;
            return this;
        }

        public Builder withIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder withFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder withUrlThumbnail(String urlThumbnail) {
            this.urlThumbnail = urlThumbnail;
            return this;
        }

        public Comic build() {
            return new Comic(title, description, pages, dates, seriesTitle, creators, characters, isbn, format, urlThumbnail);
        }

    }

}