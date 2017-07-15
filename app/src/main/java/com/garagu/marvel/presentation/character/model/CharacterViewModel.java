package com.garagu.marvel.presentation.character.model;

import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.UrlViewModel;

import java.util.List;

/**
 * Created by garagu.
 */
public class CharacterViewModel {

    private final int id;
    private final String name;
    private final String description;
    private final String urlThumbnail;
    private final CollectionViewModel comics;
    private final CollectionViewModel series;
    private final CollectionViewModel stories;
    private final CollectionViewModel events;
    private final List<UrlViewModel> urls;

    private CharacterViewModel(int id, String name, String description, String urlThumbnail, CollectionViewModel comics, CollectionViewModel series, CollectionViewModel stories, CollectionViewModel events, List<UrlViewModel> urls) {
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

    public CollectionViewModel getComics() {
        return comics;
    }

    public CollectionViewModel getSeries() {
        return series;
    }

    public CollectionViewModel getStories() {
        return stories;
    }

    public CollectionViewModel getEvents() {
        return events;
    }

    public List<UrlViewModel> getUrls() {
        return urls;
    }

    public static class Builder {

        private int id;
        private String name;
        private String description;
        private String urlThumbnail;
        private CollectionViewModel comics;
        private CollectionViewModel series;
        private CollectionViewModel stories;
        private CollectionViewModel events;
        private List<UrlViewModel> urls;

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

        public Builder withComics(CollectionViewModel comics) {
            this.comics = comics;
            return this;
        }

        public Builder withSeries(CollectionViewModel series) {
            this.series = series;
            return this;
        }

        public Builder withStories(CollectionViewModel stories) {
            this.stories = stories;
            return this;
        }

        public Builder withEvents(CollectionViewModel events) {
            this.events = events;
            return this;
        }

        public Builder withUrls(List<UrlViewModel> urls) {
            this.urls = urls;
            return this;
        }

        public CharacterViewModel build() {
            return new CharacterViewModel(id, name, description, urlThumbnail, comics, series, stories, events, urls);
        }
    }

}
