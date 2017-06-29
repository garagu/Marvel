package com.garagu.marvel.domain.model;

import java.util.List;

/**
 * Created by garagu.
 */
public class Comic {

    private final String title;
    private final String description;
    private final int pages;
    private final ComicSeries series;
    private final List<ComicCreator> creators;
    private final List<ComicCharacter> characters;
    private final String isbn;
    private final String format;
    private final String urlThumbnail;

    public Comic(String title, String description, int pages, ComicSeries series, List<ComicCreator> creators, List<ComicCharacter> characters, String isbn, String format,
            String urlThumbnail) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.series = series;
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

    public ComicSeries getSeries() {
        return series;
    }

    public List<ComicCreator> getCreators() {
        return creators;
    }

    public List<ComicCharacter> getCharacters() {
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

}