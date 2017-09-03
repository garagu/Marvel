package com.garagu.marvel.presentation.comic.model;

/**
 * Created by garagu.
 */
public class ReviewViewModel {

    private final int rate;
    private final String text;
    private final String author;
    private final String date;

    private ReviewViewModel(int rate, String text, String author, String date) {
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

    public static class Builder {

        private int rate;
        private String text;
        private String author;
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

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public ReviewViewModel build() {
            return new ReviewViewModel(rate, text, author, date);
        }

    }

}