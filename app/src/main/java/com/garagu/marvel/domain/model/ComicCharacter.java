package com.garagu.marvel.domain.model;

/**
 * Created by garagu.
 */
public class ComicCharacter {

    private final String url;
    private final String name;

    public ComicCharacter(String url, String name) {
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