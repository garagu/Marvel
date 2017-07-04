package com.garagu.marvel.data.entity.character;

import com.garagu.marvel.data.entity.DefaultCollectionEntity;
import com.garagu.marvel.data.entity.ImageEntity;

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

}