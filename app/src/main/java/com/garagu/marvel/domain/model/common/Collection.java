package com.garagu.marvel.domain.model.common;

import com.garagu.marvel.domain.model.character.Link;

import java.util.List;

/**
 * Created by garagu.
 */
public class Collection {

    private final int totalNumber;
    private final List<Link> firstItems;
    private final String url;

    private Collection(int totalNumber, List<Link> firstItems, String url) {
        this.totalNumber = totalNumber;
        this.firstItems = firstItems;
        this.url = url;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public List<Link> getFirstItems() {
        return firstItems;
    }

    public String getUrl() {
        return url;
    }

    public static class Builder {

        private int totalNumber;
        private List<Link> firstItems;
        private String url;

        public Builder withTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
            return this;
        }

        public Builder withFirstItems(List<Link> firstItems) {
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
