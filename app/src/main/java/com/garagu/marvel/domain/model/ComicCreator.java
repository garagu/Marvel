package com.garagu.marvel.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by garagu.
 */
public class ComicCreator implements Parcelable {

    private String name;
    private String role;

    private ComicCreator(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public static class Builder {

        private String name;
        private String role;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public ComicCreator build() {
            return new ComicCreator(name, role);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(role);
    }

    protected ComicCreator(Parcel in) {
        name = in.readString();
        role = in.readString();
    }

    public static final Parcelable.Creator<ComicCreator> CREATOR = new Parcelable.Creator<ComicCreator>() {

        @Override
        public ComicCreator createFromParcel(Parcel source) {
            return new ComicCreator(source);
        }

        @Override
        public ComicCreator[] newArray(int size) {
            return new ComicCreator[size];
        }

    };

}