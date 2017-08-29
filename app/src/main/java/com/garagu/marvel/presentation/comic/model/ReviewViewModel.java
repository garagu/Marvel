package com.garagu.marvel.presentation.comic.model;

/**
 * Created by garagu.
 */
public class ReviewViewModel {

    private final int rate;
    private final String text;
    private final String author;
    private final int comicId;
    private final String comicTitle;
    private final String date;

    private ReviewViewModel(int rate, String text, String author, int comicId, String comicTitle, String date) {
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

    public static class Builder {

        private int rate;
        private String text;
        private String author;
        private int comicId;
        private String comicTitle;
        private String date;

        public Builder withRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withComicId(int comicId) {
            this.comicId = comicId;
            return this;
        }

        public Builder withComicTitle(String comicTitle) {
            this.comicTitle = comicTitle;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public ReviewViewModel build() {
            return new ReviewViewModel(rate, text, author, comicId, comicTitle, date);
        }

    }

}