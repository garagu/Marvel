package com.garagu.marvel.domain.model.common;

/**
 * Created by garagu.
 */
public class Review {

    private final int rate;
    private final String text;
    private final String author;
    private final String title;
    private final String date;

    public Review(int rate, String text, String author, String title, String date) {
        this.rate = rate;
        this.text = text;
        this.author = author;
        this.title = title;
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

}