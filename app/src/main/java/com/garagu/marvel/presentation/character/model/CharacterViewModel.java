package com.garagu.marvel.presentation.character.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.LinkViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class CharacterViewModel implements Parcelable {

    public static final Creator<CharacterViewModel> CREATOR = new Creator<CharacterViewModel>() {
        @Override
        public CharacterViewModel createFromParcel(Parcel source) {
            return new CharacterViewModel(source);
        }

        @Override
        public CharacterViewModel[] newArray(int size) {
            return new CharacterViewModel[size];
        }
    };

    private final int id;
    private final String name;
    private final String description;
    private final String urlThumbnail;
    private final CollectionViewModel comics;
    private final CollectionViewModel series;
    private final CollectionViewModel stories;
    private final CollectionViewModel events;
    private final List<LinkViewModel> links;

    private CharacterViewModel(int id, String name, String description, String urlThumbnail, CollectionViewModel comics, CollectionViewModel series, CollectionViewModel stories, CollectionViewModel events, List<LinkViewModel> links) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlThumbnail = urlThumbnail;
        this.comics = comics;
        this.series = series;
        this.stories = stories;
        this.events = events;
        this.links = links;
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

    public boolean isThumbnailAvailable() {
        return !TextUtils.isEmpty(urlThumbnail);
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

    public List<LinkViewModel> getLinks() {
        return links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(urlThumbnail);
        dest.writeParcelable(comics, flags);
        dest.writeParcelable(series, flags);
        dest.writeParcelable(stories, flags);
        dest.writeParcelable(events, flags);
        dest.writeList(links);
    }

    private CharacterViewModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        urlThumbnail = in.readString();
        final ClassLoader collectionClassLoader = CollectionViewModel.class.getClassLoader();
        comics = in.readParcelable(collectionClassLoader);
        series = in.readParcelable(collectionClassLoader);
        stories = in.readParcelable(collectionClassLoader);
        events = in.readParcelable(collectionClassLoader);
        links = new ArrayList<>();
        in.readList(this.links, LinkViewModel.class.getClassLoader());
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
        private List<LinkViewModel> links;

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

        public Builder withLinks(List<LinkViewModel> links) {
            this.links = links;
            return this;
        }

        public CharacterViewModel build() {
            return new CharacterViewModel(id, name, description, urlThumbnail, comics, series, stories, events, links);
        }
    }

}
