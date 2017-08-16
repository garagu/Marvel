package com.garagu.marvel.data.entity.review;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by garagu.
 */
public class ReviewEntity {

    public int rate;
    public String text;
    public String author;
    public String title;
    public String date;

    public ReviewEntity() {
    }

    public ReviewEntity(int rate, String text, String author, String title, String date) {
        this.rate = rate;
        this.text = text;
        this.author = author;
        this.title = title;
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("title", title);
        result.put("rate", rate);
        result.put("date", date);
        result.put("text", text);
        return result;
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