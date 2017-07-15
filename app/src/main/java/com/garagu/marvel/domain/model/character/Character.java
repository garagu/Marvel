package com.garagu.marvel.domain.model.character;

import com.garagu.marvel.domain.model.common.Collection;
import com.garagu.marvel.domain.model.common.Url;

import java.util.List;

/**
 * Created by garagu.
 */
public class Character {

    private final int id;
    private final String name;
    private final String description;
    private final String urlThumbnail;
    private final Collection comics;
    private final Collection series;
    private final Collection stories;
    private final Collection events;
    private final List<Url> urls;

    private Character(int id, String name, String description, String urlThumbnail, Collection comics, Collection series, Collection stories, Collection events, List<Url> urls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlThumbnail = urlThumbnail;
        this.comics = comics;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.urls = urls;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public Collection getComics() {
        return comics;
    }

    public Collection getSeries() {
        return series;
    }

    public Collection getStories() {
        return stories;
    }

    public Collection getEvents() {
        return events;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public static class Builder {

        private int id;
        private String name;
        private String description;
        private String urlThumbnail;
        private Collection comics;
        private Collection series;
        private Collection stories;
        private Collection events;
        private List<Url> urls;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withUrlThumbnail(String urlThumbnail) {
            this.urlThumbnail = urlThumbnail;
            return this;
        }

        public Builder withComicsList(Collection comics) {
            this.comics = comics;
            return this;
        }

        public Builder withSeriesList(Collection series) {
            this.series = series;
            return this;
        }

        public Builder withStoriesList(Collection stories) {
            this.stories = stories;
            return this;
        }

        public Builder withEventsList(Collection events) {
            this.events = events;
            return this;
        }

        public Builder withUrls(List<Url> urls) {
            this.urls = urls;
            return this;
        }

        public Character build() {
            return new Character(id, name, description, urlThumbnail, comics, series, stories, events, urls);
        }
    }

}