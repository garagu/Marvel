package com.garagu.marvel.data.entity.review;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by garagu.
 */
public class MyReviewEntity {

    public int rate;
    public String text;
    public String author;
    public int comicId;
    public String comicTitle;
    public String date;

    public MyReviewEntity() {
    }

    public MyReviewEntity(int rate, String text, String author, int comicId, String comicTitle, String date) {
        this.rate = rate;
        this.text = text;
        this.author = author;
        this.comicId = comicId;
        this.comicTitle = comicTitle;
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("comicId", comicId);
        result.put("comicTitle", comicTitle);
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