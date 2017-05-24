package com.garagu.marvel.domain;

import java.util.List;

/**
 * Created by garagu.
 */
public class Comic {

    private int id;
    private String title;
    private String description;
    private int pages;
    private List<ComicDate> dates;
    private String seriesTitle;
    private List<ComicCreator> creators;
    private List<String> characters;
    private String urlThumbnail;

    public int getId() {
        return id;
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

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

}