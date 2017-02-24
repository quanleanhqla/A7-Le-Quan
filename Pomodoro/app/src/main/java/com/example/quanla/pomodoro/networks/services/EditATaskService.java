package com.example.quanla.pomodoro.networks.services;

import com.example.quanla.pomodoro.databases.models.Task;
import com.example.quanla.pomodoro.networks.jsonmodels.AddTaskBodyJson;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by QuanLA on 2/23/2017.
 */

public interface EditATaskService {
    @FormUrlEncoded
    @PUT("task/{localID}")
    Call<Task> editATask(@Path("localID") String localID,
                         @Field("name") String newName,
                         @Field("color") String newColor,
                         @Field("payment_per_hour") float newPayment,
                         @Field("local_id") String newLocalID);
    @DELETE("task/{localID}")
    Call<Task> deleteATask(@Path("localID") String localID);
}
