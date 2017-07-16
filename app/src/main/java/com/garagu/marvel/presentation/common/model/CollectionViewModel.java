package com.garagu.marvel.presentation.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by garagu.
 */
public class CollectionViewModel implements Parcelable {

    public static final Creator<CollectionViewModel> CREATOR = new Creator<CollectionViewModel>() {
        @Override
        public CollectionViewModel createFromParcel(Parcel source) {
            return new CollectionViewModel(source);
        }

        @Override
        public CollectionViewModel[] newArray(int size) {
            return new CollectionViewModel[size];
        }
    };

    private final int totalNumber;
    private final List<String> firstItems;
    private final String url;

    private CollectionViewModel(int totalNumber, List<String> firstItems, String url) {
        this.totalNumber = totalNumber;
        this.firstItems = firstItems;
        this.url = url;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public List<String> getFirstItems() {
        return firstItems;
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
        dest.writeInt(totalNumber);
        dest.writeStringList(firstItems);
        dest.writeString(url);
    }

    private CollectionViewModel(Parcel in) {
        totalNumber = in.readInt();
        firstItems = in.createStringArrayList();
        url = in.readString();
    }

    public static class Builder {

        private int totalNumber;
        private List<String> firstItems;
        private String url;

        public Builder withTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
            return this;
        }

        public Builder withFirstItems(List<String> firstItems) {
            this.firstItems = firstItems;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public CollectionViewModel build() {
            return new CollectionViewModel(totalNumber, firstItems, url);
        }

    }

}
