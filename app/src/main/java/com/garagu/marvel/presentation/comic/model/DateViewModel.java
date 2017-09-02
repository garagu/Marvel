package com.garagu.marvel.presentation.comic.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by garagu.
 */

public class DateViewModel implements Parcelable {

    public static final Creator<DateViewModel> CREATOR = new Creator<DateViewModel>() {
        @Override
        public DateViewModel createFromParcel(Parcel source) {
            return new DateViewModel(source);
        }

        @Override
        public DateViewModel[] newArray(int size) {
            return new DateViewModel[size];
        }
    };

    private final String type;
    private final String date;

    private DateViewModel(String type, String date) {
        this.type = type;
        this.date = date;
    }

    protected DateViewModel(Parcel in) {
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

        public DateViewModel build() {
            return new DateViewModel(type, date);
        }

    }

}