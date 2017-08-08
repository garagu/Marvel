package com.garagu.marvel.presentation.common.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by garagu.
 */
public class LinkViewModel implements Parcelable {

    public static final Parcelable.Creator<LinkViewModel> CREATOR = new Parcelable.Creator<LinkViewModel>() {
        @Override
        public LinkViewModel createFromParcel(Parcel source) {
            return new LinkViewModel(source);
        }

        @Override
        public LinkViewModel[] newArray(int size) {
            return new LinkViewModel[size];
        }
    };

    private final String name;
    private final String url;

    private LinkViewModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }

    private LinkViewModel(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static class Builder {

        private String name;
        private String url;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public LinkViewModel build() {
            return new LinkViewModel(name, url);
        }

    }

}