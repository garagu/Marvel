package com.garagu.marvel.presentation.common.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by garagu.
 */
public class PaginatedListViewModel<T> {

    private List<T> items = Collections.emptyList();
    private boolean hasMore;
    private int offset;

    private PaginatedListViewModel(List<T> items, boolean hasMore, int offset) {
        this.items = items;
        this.hasMore = hasMore;
        this.offset = offset;
    }

    public static <TT> Builder<TT> builder() {
        return new Builder<>();
    }

    public List<T> getItems() {
        return items;
    }

    public boolean hasMore() {
        return hasMore;
    }

    public int getOffset() {
        return offset;
    }

    public static class Builder<TT> {

        private List<TT> items = Collections.emptyList();
        private boolean hasMore;
        private int offset;

        public Builder<TT> withItems(List<TT> items) {
            this.items = items;
            return this;
        }

        public Builder<TT> withHasMore(boolean hasMore) {
            this.hasMore = hasMore;
            return this;
        }

        public Builder<TT> withOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public PaginatedListViewModel<TT> build() {
            return new PaginatedListViewModel<>(items, hasMore, offset);
        }

    }

}