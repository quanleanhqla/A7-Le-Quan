package com.example.quanla.pomodoro.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QuanLA on 1/18/2017.
 */

public class NetContext {
    private Retrofit retrofit;
    public NetContext(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
