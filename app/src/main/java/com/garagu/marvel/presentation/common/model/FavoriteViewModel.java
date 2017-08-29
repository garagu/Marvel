package com.garagu.marvel.presentation.common.model;

import android.text.TextUtils;

/**
 * Created by garagu.
 */
public class FavoriteViewModel {

    private final String userId;
    private final int favoriteId;
    private final String name;
    private final String urlThumbnail;
    private final int type;

    private FavoriteViewModel(String userId, int favoriteId, String name, String thumbnail, int type) {
        this.userId = userId;
        this.favoriteId = favoriteId;
        this.name = name;
        this.urlThumbnail = thumbnail;
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

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public boolean isThumbnailAvailable() {
        return !TextUtils.isEmpty(urlThumbnail);
    }

    public int getType() {
        return type;
    }

    public static class Builder {

        private String userId;
        private int favoriteId;
        private String name;
        private String urlThumbnail;
        private int type;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFavoriteId(int favoriteId) {
            this.favoriteId = favoriteId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUrlThumbnail(String urlThumbnail) {
            this.urlThumbnail = urlThumbnail;
            return this;
        }

        public Builder withType(int type) {
            this.type = type;
            return this;
        }

        public FavoriteViewModel build() {
            return new FavoriteViewModel(userId, favoriteId, name, urlThumbnail, type);
        }

    }

}