package com.example.quanla.pomodoro.networks.jsonmodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuanLA on 1/22/2017.
 */

public class RegisterResponseJson {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "LoginResponseJson{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
