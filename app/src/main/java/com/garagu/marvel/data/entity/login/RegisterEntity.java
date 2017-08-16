package com.garagu.marvel.data.entity.login;

/**
 * Created by garagu.
 */
public class RegisterEntity extends LoginEntity {

    private final String name;

    public RegisterEntity(String email, String password, String name) {
        super(email, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}