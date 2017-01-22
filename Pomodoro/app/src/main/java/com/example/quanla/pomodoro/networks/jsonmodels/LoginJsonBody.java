package com.example.quanla.pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuanLA on 1/18/2017.
 */

public class LoginJsonBody {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LoginJsonBody(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginJsonBody{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
