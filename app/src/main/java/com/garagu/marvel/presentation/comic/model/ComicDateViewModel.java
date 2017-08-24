package com.garagu.marvel.presentation.comic.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by garagu.
 */

public class ComicDateViewModel implements Parcelable {

    public static final Creator<ComicDateViewModel> CREATOR = new Creator<ComicDateViewModel>() {
        @Override
        public ComicDateViewModel createFromParcel(Parcel source) {
            return new ComicDateViewModel(source);
        }

        @Override
        public ComicDateViewModel[] newArray(int size) {
            return new ComicDateViewModel[size];
        }
    };

    private final String type;
    private final String date;

    private ComicDateViewModel(String type, String date) {
        this.type = type;
        this.date = date;
    }

    protected ComicDateViewModel(Parcel in) {
        type = in.readString();
        date = in.readString();
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(date);
    }

    public static class Builder {

        private String type;
        private String date;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public ComicDateViewModel build() {
            return new ComicDateViewModel(type, date);
        }

    }

}