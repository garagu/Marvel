package com.garagu.marvel.domain.model.common;

import java.util.List;

/**
 * Created by garagu.
 */
public class Collection {

    private final int totalNumber;
    private final List<Url> firstItems;
    private final String url;

    private Collection(int totalNumber, List<Url> firstItems, String url) {
        this.totalNumber = totalNumber;
        this.firstItems = firstItems;
        this.url = url;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public List<Url> getFirstItems() {
        return firstItems;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {

        private int totalNumber;
        private List<Url> firstItems;
        private String url;

        public Builder withTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
            return this;
        }

        public Builder withFirstItems(List<Url> firstItems) {
            this.firstItems = firstItems;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Collection build() {
            return new Collection(totalNumber, firstItems, url);
        }

    }


}
