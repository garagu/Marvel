package com.garagu.marvel.presentation.common.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by garagu.
 */
public class UserViewModel implements Parcelable {

    public static final Parcelable.Creator<UserViewModel> CREATOR = new Parcelable.Creator<UserViewModel>() {
        @Override
        public UserViewModel createFromParcel(Parcel source) {
            return new UserViewModel(source);
        }

        @Override
        public UserViewModel[] newArray(int size) {
            return new UserViewModel[size];
        }
    };

    private final String id;
    private final String name;
    private final String email;

    private UserViewModel(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    protected UserViewModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
    }

    public static class Builder {

        private String id;
        private String name;
        private String email;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserViewModel build() {
            return new UserViewModel(id, name, email);
        }

    }

}