package com.garagu.marvel.presentation.common.model;

/**
 * Created by garagu.
 */
public class UrlViewModel {

    private final String name;
    private final String url;

    private UrlViewModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {

        private String name;
        private String url;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public UrlViewModel build() {
            return new UrlViewModel(name, url);
        }

    }

}