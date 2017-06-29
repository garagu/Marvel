package com.garagu.marvel.domain.model;

/**
 * Created by garagu.
 */
public class ComicSeries {

    private final String url;
    private final String name;

    public ComicSeries(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

}