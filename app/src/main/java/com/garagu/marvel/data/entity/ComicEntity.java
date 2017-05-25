package com.garagu.marvel.data.entity;

import java.util.Date;

/**
 * Created by garagu.
 */
public class ComicEntity {

    private int id;
    private String title;
    private String description;
    private Date modified;
    private String isbn;
    private String format;
    private int pageCount;
    private SeriesEntity series;
    private ComicDateEntity[] dates;
    private ImageEntity thumbnail;
    private CreatorListEntity creators;
    private CharacterListEntity characters;

}