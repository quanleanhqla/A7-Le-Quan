package com.example.quanla.pomodoro.settings;

/**
 * Created by QuanLA on 1/14/2017.
 */

public class LoginCredentials{

    private String username;
    private String password;

    public LoginCredentials(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
