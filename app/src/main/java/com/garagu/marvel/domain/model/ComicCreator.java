package com.garagu.marvel.domain.model;

/**
 * Created by garagu.
 */
public class ComicCreator {

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

}