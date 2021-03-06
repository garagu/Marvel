package com.garagu.marvel.domain.model.comic;

/**
 * Created by garagu.
 */
public class Creator {

    private final String url;
    private final String name;
    private final String role;

    public Creator(String url, String name, String role) {
        this.url = url;
        this.name = name;
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

}