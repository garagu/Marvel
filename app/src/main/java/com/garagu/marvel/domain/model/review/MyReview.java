package com.garagu.marvel.domain.model.review;

/**
 * Created by garagu.
 */
public class MyReview {

    private final int rate;
    private final String text;
    private final String author;
    private final int comicId;
    private final String comicTitle;
    private final String date;

    public MyReview(int rate, String text, String author, int comicId, String comicTitle, String date) {
        this.rate = rate;
        this.text = text;
        this.author = author;
        this.comicId = comicId;
        this.comicTitle = comicTitle;
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

    public int getComicId() {
        return comicId;
    }

    public String getComicTitle() {
        return comicTitle;
    }

    public String getDate() {
        return date;
    }

}