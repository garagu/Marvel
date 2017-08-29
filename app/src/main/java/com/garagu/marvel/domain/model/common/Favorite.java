package com.garagu.marvel.domain.model.common;

/**
 * Created by garagu.
 */
public class Favorite {

    private final String userId;
    private final int favoriteId;
    private final String name;
    private final String thumbnail;
    private final int type;

    public Favorite(String userId, int favoriteId, String name, String thumbnail, int type) {
        this.userId = userId;
        this.favoriteId = favoriteId;
        this.name = name;
        this.thumbnail = thumbnail;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getType() {
        return type;
    }

}