package com.garagu.marvel.data.entity.comic;

import com.garagu.marvel.data.entity.DefaultCollectionEntity;
import com.garagu.marvel.data.entity.ImageEntity;
import com.garagu.marvel.data.entity.NameEntity;

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
    private NameEntity series;
    private ComicDateEntity[] dates;
    private ImageEntity thumbnail;
    private CreatorCollectionEntity creators;
    private DefaultCollectionEntity characters;

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

    public NameEntity getSeries() {
        return series;
    }

    public ComicDateEntity[] getDates() {
        return dates;
    }

    public ImageEntity getThumbnail() {
        return thumbnail;
    }

    public CreatorCollectionEntity getCreators() {
        return creators;
    }

    public DefaultCollectionEntity getCharacters() {
        return characters;
    }

}