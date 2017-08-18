package com.garagu.marvel.data.entity.common;

/**
 * Created by garagu.
 */
public class UserEntity {

    private final String id;
    private final String name;
    private final String email;

    public UserEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

}