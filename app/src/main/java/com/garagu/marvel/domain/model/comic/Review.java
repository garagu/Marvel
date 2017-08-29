package com.garagu.marvel.domain.model.comic;

/**
 * Created by garagu.
 */
public class Review {

    private final int rate;
    private final String text;
    private final String author;
    private final String date;

    public Review(int rate, String text, String author, String date) {
        this.rate = rate;
        this.text = text;
        this.author = author;
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

    public String getDate() {
        return date;
    }

}