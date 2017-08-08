package com.garagu.marvel.domain.model.character;

import com.garagu.marvel.domain.model.common.PaginatedList;

import java.util.List;

/**
 * Created by garagu.
 */
public class PaginatedCharacterList extends PaginatedList<Character> {

    private PaginatedCharacterList(int count, int offset, int total, List<Character> list) {
        super(count, offset, total, list);
    }

    public static class Builder {

        private int count;
        private int offset;
        private int total;
        private List<Character> list;

        public Builder withCount(int count) {
            this.count = count;
            return this;
        }

        public Builder withOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder withTotal(int total) {
            this.total = total;
            return this;
        }

        public Builder withList(List<Character> list) {
            this.list = list;
            return this;
        }

        public PaginatedCharacterList build() {
            return new PaginatedCharacterList(count, offset, total, list);
        }

    }

}