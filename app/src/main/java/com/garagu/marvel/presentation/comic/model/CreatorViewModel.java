package com.garagu.marvel.presentation.comic.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by garagu.
 */
public class CreatorViewModel implements Parcelable {

    private final String name;
    private final String role;

    private CreatorViewModel(String name, String role) {
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

        public CreatorViewModel build() {
            return new CreatorViewModel(name, role);
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

    protected CreatorViewModel(Parcel in) {
        name = in.readString();
        role = in.readString();
    }

    public static final Creator<CreatorViewModel> CREATOR = new Creator<CreatorViewModel>() {

        @Override
        public CreatorViewModel createFromParcel(Parcel source) {
            return new CreatorViewModel(source);
        }

        @Override
        public CreatorViewModel[] newArray(int size) {
            return new CreatorViewModel[size];
        }

    };

}