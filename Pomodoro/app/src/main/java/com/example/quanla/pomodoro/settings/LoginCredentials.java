package com.example.quanla.pomodoro.settings;

/**
 * Created by QuanLA on 1/14/2017.
 */

public class LoginCredentials{

    private String username;
    private String password;
    private String accessToken;

//    public LoginCredentials(String username, String password) {
//        this.password = password;
//        this.username = username;
//    }

    public LoginCredentials(String accessToken, String password, String username) {
        this.accessToken = accessToken;
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
