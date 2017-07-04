package com.garagu.marvel.data.entity;

/**
 * Created by garagu.
 */
public class CollectionEntity<T> {

    private int available;
    private String collectionURI;
    private T[] items;
    private int returned;

    public int getAvailable() {
        return available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public T[] getItems() {
        return items;
    }

    public int getReturned() {
        return returned;
    }

}