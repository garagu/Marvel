package com.garagu.marvel.data.entity.login;

/**
 * Created by garagu.
 */
public class LoginEntity {

    private final String email;
    private final String password;

    public LoginEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}