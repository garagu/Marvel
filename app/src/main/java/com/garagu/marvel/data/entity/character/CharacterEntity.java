package com.garagu.marvel.data.entity.character;

import com.garagu.marvel.data.entity.common.DefaultCollectionEntity;
import com.garagu.marvel.data.entity.common.ImageEntity;

/**
 * Created by garagu.
 */
public class CharacterEntity {

    private int id;
    private String name;
    private String description;
    private ImageEntity thumbnail;
    private DefaultCollectionEntity comics;
    private DefaultCollectionEntity series;
    private DefaultCollectionEntity stories;
    private DefaultCollectionEntity events;
    private UrlEntity[] urls;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ImageEntity getThumbnail() {
        return thumbnail;
    }

    public DefaultCollectionEntity getComics() {
        return comics;
    }

    public DefaultCollectionEntity getSeries() {
        return series;
    }

    public DefaultCollectionEntity getStories() {
        return stories;
    }

    public DefaultCollectionEntity getEvents() {
        return events;
    }

    public UrlEntity[] getUrls() {
        return urls;
    }

}