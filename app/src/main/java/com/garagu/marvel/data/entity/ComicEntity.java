package com.garagu.marvel.data.entity;

/**
 * Created by garagu.
 */
public class ComicEntity {

    private int id;
    private String title;
    private String description;
    private String isbn;
    private String format;
    private int pageCount;
    private SeriesEntity series;
    private ComicDateEntity[] dates;
    private ImageEntity thumbnail;
    private CreatorListEntity creators;
    private CharacterListEntity characters;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getFormat() {
        return format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public SeriesEntity getSeries() {
        return series;
    }

    public ComicDateEntity[] getDates() {
        return dates;
    }

    public ImageEntity getThumbnail() {
        return thumbnail;
    }

    public CreatorListEntity getCreators() {
        return creators;
    }

    public CharacterListEntity getCharacters() {
        return characters;
    }

}